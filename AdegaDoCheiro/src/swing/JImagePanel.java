/*
 * Copyright 2018 Aluno.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package swing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Aluno
 */
public class JImagePanel extends JPanel {

    private Icon backgroundImage = null;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public JImagePanel() {
        super();
        setBackground(new Color(0, 0, 0, 0));
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public JImagePanel(Icon imageIcon) {
        super();
        setBackground(new Color(255, 255, 255, 255));
        setBackgroundImage(backgroundImage);
        updateBackgroundImage();
    }

    public Icon getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(Icon backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public void updateBackgroundImage() {
        paintComponent(getGraphics());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(((ImageIcon) backgroundImage).getImage(), 0, 0, this.getWidth(),
                    this.getHeight(), this);
        }
    }

} // Fim da classe JImagePanel
