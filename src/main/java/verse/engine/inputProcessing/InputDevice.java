package verse.engine.inputProcessing;

public class InputDevice {

    private IInputTranslator translator;
    private IInputProcessor processor;
    
    public InputDevice(IInputTranslator translator, IInputProcessor processor) {
        this.translator = translator;
        this.processor = processor;
    }

    public IInputTranslator getInputTranslator() {
        return this.translator;
    }

    public IInputProcessor getInputProcessor() {
        return this.processor;
    }
    
}
