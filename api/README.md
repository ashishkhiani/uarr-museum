# App Tier for Urban Alliance on Race Relations Museum

## Description
The uarr app-tier does following
- It loads to csv spreadsheet at runtime into the cache
- It provides incidents list based on various filters

## Project setup instructions

- Please install the JDK 8 from here : https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
- Please download the maven from here : https://maven.apache.org/download.cgi
- Use following command to generate the executable file
```
mvn clean package
```

## How to run the app-tier locally
- Please install the JDK 8 from here : https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
- Build the project from project setup instructions
- Run the following command to start the app-tier
```
java -Dinput.spreadsheet=path-to-csv-file -jar uarr-app-tier.jar
```

## Completed APIs

Get incident by id : http://localhost:8086/uarr/museum/incidents/{id}

Get all incidents : http://localhost:8086/uarr/museum/incidents

Get incident by filters
- Get incident by system : http://localhost:8086/uarr/museum/incidents?system=education,community,fireDepartment
- Get incident by province : http://localhost:8086/uarr/museum/incidents?province=britishcolumbia,alberta,ontario
- Get incident by incident level : http://localhost:8086/uarr/museum/incidents?incidentLevel=mezzo
- Get incident by fromDate MMddyyyy format : http://localhost:8086/uarr/museum/incidents?fromDate=11172017
- Get incident by toDate MMddyyyy format: http://localhost:8086/uarr/museum/incidents?toDate=05012018
- Get incident by fromDate to toDate : http://localhost:8086/uarr/museum/incidents?fromDate=11172017&toDate=05012018
- Get incident by bounds , format is southWestLatitude,southWestLongitude,northEastLatitude,northEastLongitude: http://localhost:8086/uarr/museum/incidents?bounds=44.671503,%20-76.724572,45.969211,-75.318322

The above-mentioned filters can be combined together as well 
- Get incident by system and incident level : http://localhost:8086/uarr/museum/incidents?system=education&incidentLevel=mezzo
- Get incident by system, incident level, fromDate, toDate : http://localhost:8086/uarr/museum/incidents?fromDate=11172017&toDate=05012018&incidentLevel=mezzo&system=education

## Data model
Date format is MMddyyyy
```
[
    {
        "id": "1",
        "incidentLevel": "Mezzo",
        "title": "Etobicoke School of the Arts",
        "date": "11012017",
        "province": "Ontario",
        "region": "GTA",
        "location": "Etobicoke",
        "address": "675 Royal York Rd, Etobicoke, ON M8Y 2T1",
        "latitude": 43.630972,
        "longitude": -79.503917,
        "system": "Education",
        "summary": "School created list of Black students to monitor. ",
        "notes": "A Toronto school principal facing sharp criticism for keeping a list of Black students quietly announced she will be transferring schools, but students whose names were on that list are calling for a stiffer punishment. Peggy Aitchison, the principal at Etobicoke School of the Arts, made the announcement on Tuesday evening via email while graduating students were celebrating their prom.\r\nAitchison outraged parents and students by compiling a list of black students and circulating it to teachers in November of 2017.",
        "incidentResponse": " They also suggest Aitchison should be fired for her actions, rather than be moved to another school.",
        "policies": "",
        "additionalLinks": [
            "https://www.cbc.ca/news/canada/toronto/etobicoke-school-black-list-1.4724307"
        ]
    },
    {
        "id": "2",
        "incidentLevel": "Mezzo",
        "title": "Racial Slur used by Ottawa University Professor",
        "date": "09232020",
        "province": "Ontario",
        "region": "Ottawa",
        "location": "Ottawa ",
        "address": "75 Laurier Ave. E, Ottawa, ON K1N 6N5",
        "latitude": 45.423139,
        "longitude": -75.683389,
        "system": "Education",
        "summary": "University Professor uses racial slur in class for a discussion.",
        "notes": " A University of Ottawa professor has apologized for using the N-word during a class discussion, and said she's worried the controversy has now been blown out of proportion by people taking extreme positions. Verushka Lieutenant-Duval used the offensive term during an art and gender class discussion. ",
        "incidentResponse": "The bilingual university suspended Lieutenant-Duval Sept. 23, after a student complained that the part-time professor used the N-word during an an art and gender class. The university has since reinstated her and offered students from her art and gender class an alternative course she said. Only one has chosen to remain in her class, she said.                                                                                                                                                                  The Quebec government has accused the University of Ottawa of violating a professor's freedom of expression when it suspended her for using the N-word in class. Premier François Legault said Tuesday he doesn't understand the university's decision and warned of a \"slippery slope.\"",
        "policies": "",
        "additionalLinks": [
            "https://www.cbc.ca/news/canada/ottawa/professor-apologizes-university-of-ottawa-1.5770946",
            "https://www.cbc.ca/news/canada/montreal/quebec-university-of-ottawa-professor-1.5767737"
        ]
    },
    {
        "id": "3",
        "incidentLevel": "Micro",
        "title": "Joshua Telemaque (17)",
        "date": "10232020",
        "province": "Ontario",
        "region": "Durham Region",
        "location": "Pickering",
        "address": "1918 Whites Rd N, Pickering, ON L1V 1R9",
        "latitude": 43.835194,
        "longitude": -79.123278,
        "system": "Education",
        "summary": "Black student's name replaced with a Monkey's name. ",
        "notes": "ABR targeted towards a student, racist slur used to describe a black student in their public year book",
        "incidentResponse": "The school sent a letter home to parents on Sunday apologizing for the “offensive, hurtful and unacceptable nature” of the comments and vowing to conduct a full investigation.",
        "policies": "Equity & Inlcusive Education Policy (https://durhamschboard.service-now.com/md?id=ddsb_md_kb_article&sys_id=497f0eb1db1933805f6be3a84b96195d) ",
        "additionalLinks": [
            "https://www.cp24.com/news/heartbroken-pickering-student-speaks-out-after-yearbook-message-honouring-grandma-replaced-with-racist-slur-1.5143023",
            "https://www.cbc.ca/news/canada/toronto/joshua-telemaque-racist-yearbook-message-st-mary-1.5761516"
        ]
    },
    {
        "id": "4",
        "incidentLevel": "Mezzo",
        "title": "Dresden",
        "date": "12061949",
        "province": "Ontario",
        "region": "Chatham-Kent",
        "location": "Dresden",
        "address": "",
        "latitude": 42.590972,
        "longitude": -82.182944,
        "system": "Community",
        "summary": "Black People not allowed service within private and public establishments ",
        "notes": "People of colour were not allowed to be served in private establishments. Council refused, citing powerlessness in the face of the private sector. Instead, the people of Dresden would decide. “Do you approve the passing of legislation compelling restaurant owners to serve, regardless of race, creed or colour?” read the Dec. 6,1949, ballot. The result: 517 against, 108 in favour. In Dresden, Ontario, all five barbershops and the sole hair salon would not serve Black men and women.",
        "incidentResponse": "Every time Dresden was mentioned in newspaper headlines, politicians’ stance that anti-discrimination legislation was unnecessary became increasingly difficult to maintain. Dresden provided Sid Blum, secretary for the Toronto branch of the JLC, with the ammunition he desperately needed to challenge popular perceptions about prejudice in Ontario.",
        "policies": "",
        "additionalLinks": [
            "https://www.nfb.ca/film/dresden_story/.",
            "https://historyofrights.ca/encyclopaedia/main-events/dresden-racial-discrimination/"
        ]
    },
    {
        "id": "5",
        "incidentLevel": "Mezzo",
        "title": "Hamilton Fire Department",
        "date": "02012015",
        "province": "Ontario",
        "region": "Hamilton",
        "location": "Dundas",
        "address": "19 Memorial Square, Dundas, ON L9H 2T3",
        "latitude": 43.266389,
        "longitude": -79.957056,
        "system": "Fire Department",
        "summary": "Racial Slur used on an employee",
        "notes": "Shawn Elliott was also suspended a further five days without pay for his role in the fight.\r\nShawn Elliott, a member of Hamilton's fire department since 2002, was originally fired by the City of Hamilton, but arbitrator James Hayes overturned Elliott's dismissal and reduced the penalty to a 10-day unpaid suspension for what was acknowledged to be \"a very troubling situation.\"",
        "incidentResponse": "John McCarthy, the firefighter Elliott described as a \"monkey,\" was hit with a five-day suspension without pay for initiating the fight. ",
        "policies": "",
        "additionalLinks": [
            "https://www.thespec.com/news/hamilton-region/2015/02/27/hamilton-firefighter-wins-back-job-after-fist-fight-racial-slur.html"
        ]
    },
    {
        "id": "6",
        "incidentLevel": "Mezzo",
        "title": "Sir George Williams University",
        "date": "01011969",
        "province": "Quebec",
        "region": "",
        "location": "Montreal",
        "address": "1455 Boulevard de Maisonneuve O, Montréal, QC H3G 1M8",
        "latitude": 45.496139,
        "longitude": 73.578111,
        "system": "Education",
        "summary": "Montreal students protest discrimination.",
        "notes": "50 years ago, Montreal students occupied a university computer room for 13 days to protest discrimination.",
        "incidentResponse": "The sit-in started on Jan. 27, 1969 and was brought to an end on Feb. 11, 1969.\r\nI, along with hundreds of other students, barricaded myself in a computer room on the ninth floor.\r\nSome 97 students were arrested, and some were given prison sentences from two weeks to three years. Bail set ranged from $1,500 to $15,000. Professor Perry Anderson was exonerated by the administration but we, the students who laid the complaints, did not get the opportunity to present our case to an impartial body.",
        "policies": "",
        "additionalLinks": [
            "https://nationalpost.com/news/canada/50-years-later-how-racism-allegations-against-a-montreal-professor-turned-into-the-greatest-student-riot-in-canadian-history",
            "https://www.cbc.ca/news/canada/montreal/the-sir-george-williams-affair-50-years-later-was-it-worth-it-1.5014518"
        ]
    },
    {
        "id": "7",
        "incidentLevel": "Mezzo",
        "title": "Queen’s University",
        "date": "01011918",
        "province": "Ontario",
        "region": "Ontario",
        "location": "Kingston ",
        "address": "99 University Ave, Kingston, ON K7L 3N6",
        "latitude": 44.22525,
        "longitude": -76.495167,
        "system": "Education",
        "summary": "Black students banned from attending medical program.",
        "notes": "The university senate at Queen’s University in Kingston, Ontario voted to ban Black students from further admission to the Queen’s medical program. They blamed their decision on the racial intolerance of local white Kingston residents for not wanting to have any physical contact with Black physicians. Queen’s University was under pressure from the American Medical Association (AMA), a professional medical organization that rated the conditions of medical schools in the United States and Canada, to expel Black students.",
        "incidentResponse": "This anti-Black restriction remained practice at Queen’s until 1965 and stayed on the books as policy until 2018, although it was not enforced.",
        "policies": "",
        "additionalLinks": [
            "https://www.thecanadianencyclopedia.ca/en/article/racial-segregation-of-black-people-in-canada"
        ]
    },
    {
        "id": "8",
        "incidentLevel": "Mezzo",
        "title": "Palace Theatre ",
        "date": "01011860",
        "province": "Ontario",
        "region": "Ontario",
        "location": "Windsor",
        "address": "300 Ouellette Ave, Windsor, ON N9A 7B4",
        "latitude": 42.317583,
        "longitude": -83.038806,
        "system": "Community",
        "summary": "Palace Theatre maintained a “Crow’s nest” for Black customers, in reference to Jim Crow segregation laws and practices.",
        "notes": "The Palace Theatre in Windsor, Ontario referred to its designated Black section as 'the Crow's Nest' while others called this restricted area 'the Monkey's cage.' ",
        "incidentResponse": "",
        "policies": "",
        "additionalLinks": [
            "https://www.thecanadianencyclopedia.ca/en/article/racial-segregation-of-black-people-in-canada",
            "https://cfrac.com/1934-2/"
        ]
    },
    {
        "id": "9",
        "incidentLevel": "Mezzo",
        "title": "Don Andrews",
        "date": "01011974",
        "province": "Ontario",
        "region": "GTA",
        "location": "Toronto",
        "address": "60 Queen St W, Toronto, ON M5H 2M3",
        "latitude": 43.652833,
        "longitude": -79.381639,
        "system": "Politics",
        "summary": "White supremacist comes in second in Toronto mayoral race",
        "notes": "Don Andrews is a self-described white racist and the leader of the Nationalist Party of Canada. He continues to run for Mayor of Toronto, most recently 2014.",
        "incidentResponse": "Toronto council changed the law that said the runner-up would take the mayor's place if the position became vacant between elections. (Today, council gets to choose the replacement.)",
        "policies": "",
        "additionalLinks": [
            "https://www.vice.com/en/article/kwpwan/we-interviewed-the-white-supremacist-running-for-mayor-of-toronto"
        ]
    },
    {
        "id": "10",
        "incidentLevel": "Mezzo",
        "title": "Alberta Public Teacher wears Blackface in School",
        "date": "05012018",
        "province": "Alberta",
        "region": "Edmonton",
        "location": "Hill Shire",
        "address": "683 Wye Rd, Sherwood Park, AB T8B 1N2",
        "latitude": 53.5115,
        "longitude": -113.272278,
        "system": "Education",
        "summary": "Public School teacher wears blackface.",
        "notes": "An Alberta school division has apologized after a staff member at a Christian school wore blackface while dressed up as a member of the British band Spice Girls.\r\nElk Island Public Schools said in a statement Monday that it is taking immediate action with the employee to address her conduct.                                                                                                                                                                    He added that most people are likely to understand that blackface is wrong, but they don't understand why, or realize that it has roots in Canadian history as well as in the United States.",
        "incidentResponse": "Education Minister David Eggen said even though the matter is being dealt with, he finds it disturbing.",
        "policies": "",
        "additionalLinks": [
            "https://www.sherwoodparknews.com/2018/05/14/school-division-sorry-for-teachers-use-of-black-face/wcm/cecc15b1-1034-f73c-9b53-4492dcf17631"
        ]
    },
    {
        "id": "11",
        "incidentLevel": "Mezzo",
        "title": "Black man killed at the Guru Nanak Sikh temple",
        "date": "05011998",
        "province": "British Columbia",
        "region": "Vancouver",
        "location": "Surrey",
        "address": "7050 120 St, Surrey, BC V3W 3M8",
        "latitude": 49.131611,
        "longitude": -122.889139,
        "system": "Community",
        "summary": "Nirmal Singh Gill murdered by 5 skinheads at work.",
        "notes": "Nirmal Singh Gill, 65, was killed by skinheads in January 1998, beaten to death in the parking lot of the Guru Nanak Sikh temple on Scott Road during his late night watch. Two men were sentenced to 18 years in prison for the crime while another three men got 15 years each.",
        "incidentResponse": "Provincial politicians were quick to paint a different picture. “British Columbians are the most progressive people in Canada,” objected Attorney General Ujjal Dosanjh. “I don’t think it serves a purpose to talk about B.C. as the hate capital.” Surrey Mayor Doug McCallum, meanwhile, recalled that his city won an award from other Canadian municipalities last year for good race relations. But those arguments paled against the brutality of the crime behind the charges—and the patchwork of white supremacist connections that last week’s arrests exposed. ",
        "policies": "",
        "additionalLinks": [
            "https://archive.macleans.ca/article/1998/5/4/crimes-of-hate",
            "https://www.surreynowleader.com/news/vigil-for-surrey-sikh-temple-caretaker-victims-of-quebec-mosque-shooting-at-holland-park-on-saturday/"
        ]
    }
]
```


