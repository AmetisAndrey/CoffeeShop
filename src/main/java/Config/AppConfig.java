package Config;

import javax.swing.plaf.ColorUIResource;

public class AppConfig {
        private static final AppConfig INSTANCE = new AppConfig();

        private String currency = "₽";
        private AppConfig(){
        }

        public static AppConfig getInstance(){
            return INSTANCE;
        }

        public String getCurrency(){
            return currency;
        }
        public void setCurrency(String currency){
            this.currency = currency;
        }
}

