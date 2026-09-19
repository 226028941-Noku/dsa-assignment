class Node {String studentNumber;
            String studentName;
            String serviceType;
            int estimatedTime;
            Node next;
}
class LinkedList { 
            Node head;

void insertAtBeginning(Node newNode) {
    newNode.next = head;
    head = newNode;
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

 void insertAtPosition (Node newNode , int position){
        if (position==1){
        newNode.next=head;
        head=newNode;
        return;
}
Node current=head;
for (int i = 1; i <= position - 2 && current != null; i++) {
    current = current.next;
}

newNode.next = current.next;
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
