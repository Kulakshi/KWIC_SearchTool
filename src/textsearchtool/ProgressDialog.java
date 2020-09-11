/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textsearchtool;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Bashi
 */

public class ProgressDialog extends JDialog {

        private JLabel message;
        private JLabel subMessage;
        private JProgressBar progressBar;

        public ProgressDialog(Component parent, SwingWorker worker, String messageText, String subMessagetext) {

            super(parent == null ? null : SwingUtilities.getWindowAncestor(parent));
            setModal(true);

            ((JComponent)getContentPane()).setBorder(new EmptyBorder(8, 8, 8, 8));

            message = new JLabel(messageText);
            subMessage = new JLabel(subMessagetext);
            progressBar = new JProgressBar();

            setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(2, 2, 2, 2);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            add(message, gbc);

            gbc.gridy++;
            add(subMessage, gbc);

            gbc.gridy++;
            gbc.weightx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            add(progressBar, gbc);

            pack();

            worker.addPropertyChangeListener(new PropertyChangeHandler());
            switch (worker.getState()) {
                case PENDING:
                    worker.execute();
                    break;
            }

        }

        public static void showProgress(Component parent, SwingWorker worker, String message, String subMessage) {

            ProgressDialog dialog = new ProgressDialog(parent, worker, message, subMessage);
            dialog.setLocationRelativeTo(parent);
            dialog.setVisible(true);

        }



        class PropertyChangeHandler implements PropertyChangeListener {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("state")) {
                    SwingWorker.StateValue state = (SwingWorker.StateValue) evt.getNewValue();
                    switch (state) {
                        case DONE:
                            dispose();
                            break;
                    }
                } else if (evt.getPropertyName().equals("progress")) {
                    progressBar.setValue((int)evt.getNewValue());
                }
            }

        }
}
