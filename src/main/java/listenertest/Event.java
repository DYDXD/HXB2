package listenertest;

/**
 * @Author:XiongbinHuang
 * @Description
 * @Date:Created in 9:51 2019/3/25
 * @Modified By:
 */
public class Event {

    private Person person;

    /*构造方法*/
    public Event(Person person){
        super();
        this.person=person;

    }

    public Event(){
        super();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
