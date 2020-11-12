package com.prime.validation.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import org.passay.DictionaryRule;
import org.passay.Rule;
import org.passay.LengthRule;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.WhitespaceRule;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.PasswordData;
import org.passay.dictionary.WordListDictionary;
import org.passay.dictionary.WordLists;
import org.passay.dictionary.sort.ArraysSort;

@Slf4j
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    private DictionaryRule dictionaryRule;

    @Override
    public void initialize(ValidPassword constraintAnnotation) throws RuntimeException {
        try {
            String invalidPasswordList = this.getClass().getResource("/invalid-password-list.txt").getFile();
            dictionaryRule = new DictionaryRule(
                    new WordListDictionary(WordLists.createFromReader(
                            new FileReader[] {
                                    new FileReader(invalidPasswordList)
                            },
                            false,
                            new ArraysSort()
                    )));
        } catch (IOException e) {
            log.error("Could not load word list {}",e);
        }
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        List<Rule> list = Arrays.asList(
                new LengthRule(7, 30),
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new WhitespaceRule(),
                dictionaryRule
        );
        PasswordValidator validator = new PasswordValidator(list);
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);
        String messageTemplate = messages.stream().collect(Collectors.joining(","));
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }

}
