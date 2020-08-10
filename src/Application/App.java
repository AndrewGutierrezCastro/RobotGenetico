package Application;

import java.util.HashMap;

import controller.VInicioController;
import controller.VPrincipalController;
import controller.ViewController;


public class App {
		static App self;
		public static HashMap<String, ViewController> controllerMap;
		
		public static App getInstance() {
			if (self == null)
				self = new App();
			return self;
		}
		public static void end() { 

		}
		private App() {
			
			controllerMap = new HashMap<String, ViewController>();
		}
		public static void preload() {
			//precarga de todos los controladores
			controllerMap.put("VINICIOCONTROLLER", new VInicioController());
		}
		public static void start() {
			preload();
			controllerMap.get("VINICIOCONTROLLER").show();
		}
}
