package cz.zcu.kiv.eegmongo.controller.validator;

import cz.zcu.kiv.eegmongo.model.UserItem;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author: Jan Koreň
 * @email: korenjan@students.zcu.cz
 * Date: 15.5.12
 */
@Component
public class UserValidator implements Validator{

    @Override
    public boolean supports(Class<?> aClass) {
        return UserItem.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        UserItem userItem = (UserItem) o;

        if(userItem.getHeight() == null || userItem.getHeight() == 0) {
            userItem.setHeight(0);
        }
        else {
            if(userItem.getHeight() < 50 ) {
                errors.rejectValue("height", "userItem.height.minValue");
            }
            else if(userItem.getHeight() > 270 ) {
                errors.rejectValue("height", "userItem.height.maxValue");
            }
        }
    }
}
