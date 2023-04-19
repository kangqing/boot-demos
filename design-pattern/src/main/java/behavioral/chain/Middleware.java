package behavioral.chain;

/**
 * 中间件，责任链
 * @author kangqing
 * @since 2023/4/20 07:41
 */
public abstract class Middleware {
    private Middleware next;

    public static Middleware link(Middleware first, Middleware... chains) {
        Middleware head = first;
        for (Middleware nextInChain : chains) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }

    // 子类实现具体的检查
    public abstract boolean check(String email, String password);

    // 执行链表中的所有检查，下一个
    protected boolean checkNext(String email, String password) {
        if (next == null) {
            return true;
        }
        return next.check(email, password);
    }
}
