package be.dragoncave.web;

import com.vaadin.ui.Label;
import com.vaadin.ui.Table;

/**
 * Created by benoit on 04/11/2016.
 */
public class RuleGenerator implements Table.ColumnGenerator {
    @Override
    public Object generateCell(Table source, Object itemId, Object columnId) {
        Label label = new Label();
        StringBuilder labelContent = new StringBuilder();

        label.setValue(labelContent.toString());
        return label;
    }
}
