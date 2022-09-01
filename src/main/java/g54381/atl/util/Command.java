package g54381.atl.util;

/**
 * Command interface.
 */
public interface Command {

    /**
     * Executes the specified command.
     */
    void execute();

    /**
     * Undoes the specified command.
     */
    void unexecute();

}
