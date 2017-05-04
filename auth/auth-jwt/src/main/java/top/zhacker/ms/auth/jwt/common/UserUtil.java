package top.zhacker.ms.auth.jwt.common;

public final class UserUtil {
    private static ThreadLocal<BaseUser> user = new ThreadLocal<>();

    public UserUtil() {
    }

    public static void putCurrentUser(BaseUser baseUser) {
        user.set(baseUser);
    }

    public static <T extends BaseUser> T getCurrentUser() {
        return (T)user.get();
    }

    public static void clearCurrentUser() {
        user.remove();
    }

    public static String getUserId() {
        BaseUser baseUser = (BaseUser)user.get();
        return null != baseUser?baseUser.getId():null;
    }
}
