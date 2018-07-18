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

package values;

import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;

import java.util.Properties;

import javax.swing.UIManager;

/**
 *
 * @author Aluno
 */
public class SoftConfig {

    private static final String LOOK_AND_FELL = "com.jtattoo.plaf.graphite.GraphiteLookAndFeel";
    public static final int WINDOW_WIDTH = 726;
    public static final String BACKUP_FOLDER_PATH = System.getProperty("user.home") + 
            "\\Documents\\AC Database Backups";

    private static void setJTattooCurrentTheme() {
        Properties props = new Properties();
        props.put("logoString", "AC");
        props.put("centerWindowTitle", "on");
        props.put("textAntiAliasing", "on");
        GraphiteLookAndFeel.setCurrentTheme(props);
    } // Fim do método setJTottooCurrentTheme
    
    public static void setLookAndFeel() {
        try {
            SoftConfig.setJTattooCurrentTheme();
            UIManager.setLookAndFeel(SoftConfig.LOOK_AND_FELL);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.out.println("Error: setLookAndFeel: " + ex);
        }
    } // Fim do método setLookAndFeel

} // Fim da classe SoftConfig
