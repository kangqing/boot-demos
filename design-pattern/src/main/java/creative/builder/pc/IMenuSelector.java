package creative.builder.pc;

/**
 * @author kangqing
 * @since 2023/7/6 09:41
 */
public interface IMenuSelector {

    IMenuSelector appendKeyboard(IPCSelector ipcSelector);

    IMenuSelector appendMouse(IPCSelector ipcSelector);

    IMenuSelector appendSound(IPCSelector ipcSelector);

    IMenuSelector appendCamera(IPCSelector ipcSelector);

    String getDetail();
}
