package verse.engine;

import java.util.List;
import java.util.ArrayList;

/**
 * An Engine is an abstract representation of an game engine. Like any Engine, it runs on cogs, more specifically IEngineCogs. It features three 
 * distinct categories of cog; Input Processing cogs, Update oriented cogs, and Graphic cogs. 
 * 
 *
 * @author Faiz Chaudhry
 */
public abstract class Engine implements Runnable{

    public static enum CogType {UPDATE, INPUT, GRAPHIC}
    
    private List<IEngineCog> inputCogs, updateCogs, graphicCogs;

    public Engine() {
        this.initCogs();
    }

    private void initCogs() {
        this.setCogs(new ArrayList<IEngineCog>(), CogType.INPUT);
        this.setCogs(new ArrayList<IEngineCog>(), CogType.UPDATE);
        this.setCogs(new ArrayList<IEngineCog>(), CogType.GRAPHIC);
    }

    public void setCogs(List<IEngineCog> cogs, CogType type) {
        switch(type) {

        case UPDATE:
            this.inputCogs = cogs;
            break;

        case INPUT:
            this.updateCogs = cogs;
            break;

        case GRAPHIC:
            this.graphicCogs = cogs;
            break;

        default:
            System.out.println("Some Error For Now");
            break;
        }
        
    }

    public void addCog(IEngineCog cog, CogType type) {
        switch(type) {

        case UPDATE:
            this.inputCogs.add(cog);
            break;

        case INPUT:
            this.updateCogs.add(cog);
            break;

        case GRAPHIC:
            this.graphicCogs.add(cog);
            break;

        default:
            System.out.println("Some Error For Now");
            break;
        }

    }

    public IEngineCog getCog(int index, CogType type) {

        IEngineCog cog = null;
        
        switch(type) {
            
        case UPDATE:
            cog = this.inputCogs.get(index);
            break;

        case INPUT:
            cog = this.updateCogs.get(index);
            break;

        case GRAPHIC:
            cog = this.graphicCogs.get(index);
            break;

        default:
            System.out.println("Some Error For Now");
            break;
        }

        return cog;

    }

    public void removeCog(IEngineCog cog, CogType type) {

        switch(type) {
            
        case UPDATE:
            this.inputCogs.remove(cog);
            break;

        case INPUT:
            this.updateCogs.remove(cog);
            break;

        case GRAPHIC:
            this.graphicCogs.remove(cog);
            break;

        default:
            System.out.println("Some Error For Now");
            break;
        }

    }

    protected List<IEngineCog> getInputCogs() {
        return this.inputCogs;
    }
    
    protected List<IEngineCog> getUpdateCogs() {
        return this.updateCogs;
    }

    protected List<IEngineCog> getGraphicCogs() {
        return this.graphicCogs;
    }

}
