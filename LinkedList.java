// Task A2 - Singly Linked List

class Node {String studentNumber;
            String studentName;
            String serviceType;
            int estimatedTime;
            Node next;
}
class LinkedList { 
            Node head;
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
} // the inserting part

void deleteStudent(String studentNumber){
         if (head==null){ // this part checks if the list is empty 
return;
}
if (head.studentNumber.equals(studentNumber)){
head=head.next; //this part deletes if the student is first
return;
}
Node current=head;
while (current.next !=null && !current.next.studentNumber.equals(studentNumber)){
current=current.next;
}
if (current.next==null){
return;
}
current.next=current.next.next}// this one is how you delete

}