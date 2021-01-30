package Group10.example.API.Exception;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;


public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if(password==null) {
            password="";
        }
        PasswordValidator validator = new PasswordValidator(Arrays.asList(

                //At least one special character
                //new CharacterRule(EnglishCharacterData.Special, 1),
                //password length should be length of 8 min
                new LengthRule(8, 30),
                //no white spaces
                new WhitespaceRule(),
                //At least one Upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                //At least one Lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                //At least one digit
                new CharacterRule(EnglishCharacterData.Digit, 1)

        ));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);

        String messageTemplate = messages.stream()
                .collect(Collectors.joining(","));
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }
}


