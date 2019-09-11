class Person{
  String _firstName;
  String _lastName;
  String _phone;

  Person(this._firstName, this._lastName, this._phone);

  toString() => "$_firstName $_lastName $_phone";
}

void main(){
  List<Person> list = [
    Person("Kaneki", "Ken", "None"),
    Person("Vlad", "Kennedy", "None"),
    Person("Light", "Yagami", "None")
  ];

  print("Not sorted list: ${list}");

  list.sort((a, b) => a._firstName.compareTo(b._firstName));
  print("Sorted list by name: $list");
}