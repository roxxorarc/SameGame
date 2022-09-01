package g54381.atl.util;


import java.util.Stack;

public class UndoManager {
    private final Stack<Command> undoStack = new Stack<>();
    private final Stack<Command> redoStack = new Stack<>();


    public UndoManager() {

    }

    public void addCommand(Command command) {
        undoStack.push(command);
        command.execute();
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.empty()) {
            Command command = undoStack.pop();
            command.unexecute();
            redoStack.push(command);
        }
    }

    public void redo() {
        if (!redoStack.empty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        }
    }

    public void reset() {
        undoStack.clear();
        redoStack.clear();
    }
}
