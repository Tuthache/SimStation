package mvc;

public abstract class Model extends Bean {
    public boolean unsavedChanges;
    public String fileName;

    public Model() {
        unsavedChanges = false;
        fileName = null;
    }

    public void changed() {
        unsavedChanges = true;
        firePropertyChange("ChangesMade", null, null);
    }

    public boolean getUnsavedChanges() {
        return unsavedChanges;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fName) {
        fileName = fName;
    }

    public void setUnsavedChanges(boolean b) {
        unsavedChanges = b;
    }
}
