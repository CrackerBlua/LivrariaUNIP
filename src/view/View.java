package view;

/** 
 * @Interface: View 
 * 	Essa classe é responsável de abstrair os métodos, para que as classes concretas implementem
 *  e possam implementar regras de negócios diferentes
 **/

public interface View {
	public void init();
	public void dispatchBDEvent(String context);
}
