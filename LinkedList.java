class Node {String studentNumber;
            String studentName;
            String serviceType;
            int estimatedTime;
            Node next;
}
class StudentServiceList { 
            Node head;

void insertStudent(Node newNode, int position) {
    if (position == 1) {
        newNode.next = head;
        head = newNode;
        return;
    }
    Node current = head;
    for (int i = 1; i < position - 1; i++) {
        current = current.next;
    }
    newNode.next = current.next;
    current.next = newNode;
}
void insertAtEnd(Node newNode) {
    if (head == null) {
        head = newNode;
        return;
}

    Node current = head;

    while (current.next != null) {
        current = current.next;
}

    current.next = newNode;
}
void deleteStudent(String studentNumber){
         if (head==null){ 
return;
}
if (head.studentNumber.equals(studentNumber)){
head=head.next; 
return;
}
Node current=head;
while (current.next !=null && !current.next.studentNumber.equals(studentNumber)){
current=current.next;
}
if (current.next==null){
return;
}
current.next=current.next.next;
}

void searchStudent(String studentNumber){
Node current = head;
while (current !=null){
if (current.studentNumber.equals(studentNumber)){
System.out.println("Student found");
return;
}
current=current.next;
}
System.out.println("Student not found");
}

void displayStudents() {
    if (head == null) {
        return;
    }
    Node current = head;
    while (current != null) {
        System.out.println("Student Number: " + current.studentNumber);
        System.out.println("Student Name: " + current.studentName);
        System.out.println("Service Type: " + current.serviceType);
        System.out.println("Estimated Time: " + current.estimatedTime);
        current = current.next;
}
}
}
class Main {
public static void main(String[] args) {
    StudentServiceList list = new StudentServiceList();

    //Adding Maria at the end
Node maria = new Node();
    maria.studentNumber = "221045678";
    maria.studentName = "Maria";
    maria.serviceType = "Registration";
    maria.estimatedTime = 12;

    list.insertAtEnd(maria);

    //Adding Tomas at the end
Node tomas = new Node();
    tomas.studentNumber = "222034512";
    tomas.studentName = "Tomas";
    tomas.serviceType = "Student Card";
    tomas.estimatedTime = 5;

    list.insertAtEnd(tomas);

    //Adding Simon at the beginning
Node simon = new Node();
    simon.studentNumber = "221067341";
    simon.studentName = "Simon";
    simon.serviceType = "Documents";
    simon.estimatedTime = 4;
    list.insertStudent (simon, 1);

    //Adding Ndapewa at postion 3
Node ndapewa = new Node();
    ndapewa.studentNumber = "223041876";
    ndapewa.studentName = "Ndapewa";
    ndapewa.serviceType = "Fees";
    ndapewa.estimatedTime = 8;
    list.insertStudent(ndapewa, 3);

    //deleting Ndapewa
    list.deleteStudent("223041876");

    //Searching for a student that does not exist
    list.searchStudent("999999999");

    //Displaying the list
    list.displayStudents();

}
}
