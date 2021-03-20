package org.geektimes.projects.user.validator.bean.validation;

import org.apache.commons.lang.StringUtils;
import org.geektimes.projects.user.domain.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserMobileValidAnnotationValidator implements ConstraintValidator<UserMoblieValid, String> {

    /**
     * 移动电话
     */
    public final static Pattern MOBILE = Pattern.compile("(?:0|86|\\+86)?1[3-9]\\d{9}");

    public void initialize(UserMoblieValid annotation) {

    }

    /**
     * 校验手机号
     * @param value 传入参数
     * @param context 校验上下文
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        // 获取模板信息
        context.getDefaultConstraintMessageTemplate();
        if (StringUtils.isNotBlank(value)) {
            return isMatch(value, MOBILE);
        } else {
            return false;
        }

    }

    private static Boolean isMatch(final String string, final Pattern pattern) {
        return pattern.matcher(string).matches();
    }
}
