package ProgramaT;

import org.springframework.stereotype.Component;

@Component("Cualquiernombre")
public class MyComponentBeanTwo implements MyComponent{

	@Override
	public void printSomething() {
		System.out.println("Imprimiendo algo desde el MyComponenteTwo");
	}

}
