This is a kotlin application made by Onuralp AVCI.

Application has 3 different classes called "Hipo.kt", "Member.kt", and "MemberList.kt"

Hipo.kt: This is a simple class which has two properties: 'position' is a string which has job position of the member 'yearsInHipo' is an integer which has work experience of the member. Also it has a copy method which returns a clone instance of itself

Member.kt: This is a relatively more complicated class. It has basic properties like name, age, location, etc. It has a specific constructor which is constructor(json: JSONObject) : this(). This constructor gets a json map and creates a member from this. Also it has a copy method which returns a clone instance of itself

MemberList.kt: This class again has a simple interface. It contains a list of members inside. It has to different constructors:

constructor(json: JSONArray) : this() => This constructor creates member list from JSONarray
constructor(memberList: MemberList): this() => This one is copy constructor

At initialization of the program, makeRequest() method is called which gets the json file from assets of the project and intiliaze MemberList object. Member list is created from the array called filteredMemberList. At first it is same with memberList list, but as users are searching for names, this list is modified which also affects the UI accordingly.

Lastly, 'ADD NEW USER' button creates a new instance of member who is me (Onuralp AVCI). It is added to the memberList and at the same time filteredMemberList is also updated. User can add as many new users as they want. Scroll view let's use to see every user in one single page.

Sample video of the app: https://drive.google.com/file/d/1G20TrrBEBjFbphkeONY2DTyvOOKClc2C/view?usp=sharing
