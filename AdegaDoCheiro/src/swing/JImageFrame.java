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

import javax.swing.Icon;
import javax.swing.JFrame;

/**
 *
 * @author Aluno
 */
public class JImageFrame extends JFrame {

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public JImageFrame() {
        super();
        setContentPane(new JImagePanel());
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public JImageFrame(Icon backgroundImage) {
        super();
        ((JImagePanel) getContentPane()).setBackgroundImage(backgroundImage);
    }

    public Icon getBackgroundImage() {
        return ((JImagePanel) getContentPane()).getBackgroundImage();
    }

    public void setBackgroundImage(Icon backgroundImage) {
        ((JImagePanel) getContentPane()).setBackgroundImage(backgroundImage);
    }

    public void updateBackgroundImage() {
        ((JImagePanel) getContentPane()).updateBackgroundImage();
    }

} // Fim da classe JImageFrame
