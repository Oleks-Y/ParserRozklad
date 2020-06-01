# Курсовая работа по ИПЗ-3 api-kpi-rozklad

## Тема: "Парсер для приложения на IOS [KPI rozklad](https://github.com/ddanilyuk/kpiRozklad)"

## Парсер Java Spring

Стародубцев Илья и Стрилецкий Вадим ИВ-82

- [Сервер](https://github.com/IlyaStarDR/api-kpi-rozklad) парсит данные с официального сайта [kpi-rozklad](http://rozklad.kpi.ua/Schedules/ScheduleGroupSelection.aspx) и записывает данные в БД MySQL

- Сделана документация ко всему проекту и проект покрыт тестами.

## Пример API: 

#### 	Обновить БД групп

**URL**: `/updateGroups`

**Request type**: `GET`

**Server successful answer**: 

```json
succsess
```

#### 	Обновить БД пар

**URL**: `/updateLessons`

**Request type**: `GET`

**Server successful answer**: 

```json
succsess
```


#### 	Получить пары по id группы

**URL**: `/getGroupAndLessonsById/{id}`

**Request type**: `GET`

**Server successful answer**: 

```json
{
  "groupId" : 467,
  "groupName" : "ІВ-82",
  "groupUrl" : "http://rozklad.kpi.ua/Schedules/ViewSchedule.aspx?g=da7cc0da-31bb-4729-a727-14fe4b9a9851",
  "lessons" : [ {
    "lessonID" : 64421,
    "dayNumber" : "1",
    "nameDay" : "Понеділок",
    "lessonNumber" : "3",
    "numberWeek" : "1",
    "timeStartLesson" : "08:30",
    "timeEndLesson" : "10:05",
    "lessonName" : "Алгоритми та методи обчислень",
    "teacherName" : "проф. Новотарський М. А.",
    "roomNumber" : "302-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64422,
    "dayNumber" : "1",
    "nameDay" : "Понеділок",
    "lessonNumber" : "4",
    "numberWeek" : "1",
    "timeStartLesson" : "08:30",
    "timeEndLesson" : "10:05",
    "lessonName" : "Стратег. охор. навкол. середовища",
    "teacherName" : "ст.вик. Радовенчик Я. В.",
    "roomNumber" : "302-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64423,
    "dayNumber" : "1",
    "nameDay" : "Понеділок",
    "lessonNumber" : "1",
    "numberWeek" : "2",
    "timeStartLesson" : "08:30",
    "timeEndLesson" : "10:05",
    "lessonName" : "Інженерія програмного забезпечення-3. Проектування програмного забезпечення",
    "teacherName" : "доц. Болдак А. О.",
    "roomNumber" : "111-18",
    "roomType" : "Лаб"
  }, {
    "lessonID" : 64424,
    "dayNumber" : "1",
    "nameDay" : "Понеділок",
    "lessonNumber" : "2",
    "numberWeek" : "2",
    "timeStartLesson" : "08:30",
    "timeEndLesson" : "10:05",
    "lessonName" : "Інженерія програмного забезпечення-3. Проектування програмного забезпечення",
    "teacherName" : "доц. Болдак А. О.",
    "roomNumber" : "302-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64425,
    "dayNumber" : "1",
    "nameDay" : "Понеділок",
    "lessonNumber" : "3",
    "numberWeek" : "2",
    "timeStartLesson" : "08:30",
    "timeEndLesson" : "10:05",
    "lessonName" : "Алгоритми та методи обчислень",
    "teacherName" : "проф. Новотарський М. А.",
    "roomNumber" : "302-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64426,
    "dayNumber" : "2",
    "nameDay" : "Вівторок",
    "lessonNumber" : "1",
    "numberWeek" : "1",
    "timeStartLesson" : "10:25",
    "timeEndLesson" : "12:00",
    "lessonName" : "Стратег. охор. навкол. середовища",
    "teacherName" : "ст.вик. Радовенчик Я. В.",
    "roomNumber" : "231-18",
    "roomType" : "Прак"
  }, {
    "lessonID" : 64427,
    "dayNumber" : "2",
    "nameDay" : "Вівторок",
    "lessonNumber" : "2",
    "numberWeek" : "1",
    "timeStartLesson" : "10:25",
    "timeEndLesson" : "12:00",
    "lessonName" : "Методи оптимізації та планування експерименту",
    "teacherName" : "доц. Селіванов В. Л.",
    "roomNumber" : "301-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64428,
    "dayNumber" : "2",
    "nameDay" : "Вівторок",
    "lessonNumber" : "3",
    "numberWeek" : "1",
    "timeStartLesson" : "10:25",
    "timeEndLesson" : "12:00",
    "lessonName" : "Фізичне виховання",
    "teacherName" : null,
    "roomNumber" : null,
    "roomType" : null
  }, {
    "lessonID" : 64429,
    "dayNumber" : "2",
    "nameDay" : "Вівторок",
    "lessonNumber" : "2",
    "numberWeek" : "2",
    "timeStartLesson" : "10:25",
    "timeEndLesson" : "12:00",
    "lessonName" : "Методи оптимізації та планування експерименту",
    "teacherName" : "доц. Селіванов В. Л.",
    "roomNumber" : "301-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64430,
    "dayNumber" : "2",
    "nameDay" : "Вівторок",
    "lessonNumber" : "3",
    "numberWeek" : "2",
    "timeStartLesson" : "10:25",
    "timeEndLesson" : "12:00",
    "lessonName" : "Фізичне виховання",
    "teacherName" : null,
    "roomNumber" : null,
    "roomType" : null
  }, {
    "lessonID" : 64431,
    "dayNumber" : "3",
    "nameDay" : "Середа",
    "lessonNumber" : "2",
    "numberWeek" : "1",
    "timeStartLesson" : "12:20",
    "timeEndLesson" : "13:55",
    "lessonName" : "Комп'ютерна електроніка",
    "teacherName" : "ст.вик. Виноградов Ю. М.",
    "roomNumber" : "301-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64432,
    "dayNumber" : "3",
    "nameDay" : "Середа",
    "lessonNumber" : "3",
    "numberWeek" : "1",
    "timeStartLesson" : "12:20",
    "timeEndLesson" : "13:55",
    "lessonName" : "Комп'ютерна електроніка",
    "teacherName" : "ст.вик. Виноградов Ю. М.",
    "roomNumber" : "514-18",
    "roomType" : "Лаб"
  }, {
    "lessonID" : 64433,
    "dayNumber" : "3",
    "nameDay" : "Середа",
    "lessonNumber" : "4",
    "numberWeek" : "1",
    "timeStartLesson" : "12:20",
    "timeEndLesson" : "13:55",
    "lessonName" : "Архітектура комп'ютерів-1. Арифметичні та управляючі пристрої",
    "teacherName" : "доц. Верба О. А.",
    "roomNumber" : "535-18",
    "roomType" : "Лаб"
  }, {
    "lessonID" : 64434,
    "dayNumber" : "3",
    "nameDay" : "Середа",
    "lessonNumber" : "2",
    "numberWeek" : "2",
    "timeStartLesson" : "12:20",
    "timeEndLesson" : "13:55",
    "lessonName" : "Комп'ютерна електроніка",
    "teacherName" : "ст.вик. Виноградов Ю. М.",
    "roomNumber" : "301-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64435,
    "dayNumber" : "3",
    "nameDay" : "Середа",
    "lessonNumber" : "3",
    "numberWeek" : "2",
    "timeStartLesson" : "12:20",
    "timeEndLesson" : "13:55",
    "lessonName" : "Методи оптимізації та планування експерименту",
    "teacherName" : "ас. Регіда П. Г.",
    "roomNumber" : "513-18",
    "roomType" : "Лаб"
  }, {
    "lessonID" : 64436,
    "dayNumber" : "3",
    "nameDay" : "Середа",
    "lessonNumber" : "4",
    "numberWeek" : "2",
    "timeStartLesson" : "12:20",
    "timeEndLesson" : "13:55",
    "lessonName" : "Архітектура комп'ютерів-1. Арифметичні та управляючі пристрої",
    "teacherName" : "доц. Верба О. А.",
    "roomNumber" : "535-18",
    "roomType" : "Лаб"
  }, {
    "lessonID" : 64437,
    "dayNumber" : "4",
    "nameDay" : "Четвер",
    "lessonNumber" : "1",
    "numberWeek" : "1",
    "timeStartLesson" : "14:15",
    "timeEndLesson" : "15:50",
    "lessonName" : "Іноземна мова загальнотехнічного спрямування",
    "teacherName" : "вик. Сергеєва О. О.",
    "roomNumber" : "337-A-18",
    "roomType" : "Лаб"
  }, {
    "lessonID" : 64438,
    "dayNumber" : "4",
    "nameDay" : "Четвер",
    "lessonNumber" : "2",
    "numberWeek" : "1",
    "timeStartLesson" : "14:15",
    "timeEndLesson" : "15:50",
    "lessonName" : "Системне програмування-1",
    "teacherName" : "ст.вик. Порєв В. М.",
    "roomNumber" : "536-18",
    "roomType" : "Лаб"
  }, {
    "lessonID" : 64439,
    "dayNumber" : "4",
    "nameDay" : "Четвер",
    "lessonNumber" : "3",
    "numberWeek" : "1",
    "timeStartLesson" : "14:15",
    "timeEndLesson" : "15:50",
    "lessonName" : "Архітектура комп'ютерів-1. Арифметичні та управляючі пристрої",
    "teacherName" : "проф. Жабін В. І.",
    "roomNumber" : "303-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64440,
    "dayNumber" : "4",
    "nameDay" : "Четвер",
    "lessonNumber" : "5",
    "numberWeek" : "1",
    "timeStartLesson" : "14:15",
    "timeEndLesson" : "15:50",
    "lessonName" : "Іноземна мова загальнотехнічного спрямування",
    "teacherName" : "",
    "roomNumber" : "712-07",
    "roomType" : "Прак"
  }, {
    "lessonID" : 64441,
    "dayNumber" : "4",
    "nameDay" : "Четвер",
    "lessonNumber" : "1",
    "numberWeek" : "2",
    "timeStartLesson" : "14:15",
    "timeEndLesson" : "15:50",
    "lessonName" : "Іноземна мова загальнотехнічного спрямування",
    "teacherName" : "вик. Сергеєва О. О.",
    "roomNumber" : "337-A-18",
    "roomType" : "Лаб"
  }, {
    "lessonID" : 64442,
    "dayNumber" : "4",
    "nameDay" : "Четвер",
    "lessonNumber" : "2",
    "numberWeek" : "2",
    "timeStartLesson" : "14:15",
    "timeEndLesson" : "15:50",
    "lessonName" : "Алгоритми та методи обчислень",
    "teacherName" : "ст.вик. Порєв В. М.",
    "roomNumber" : "536-18",
    "roomType" : "Лаб"
  }, {
    "lessonID" : 64443,
    "dayNumber" : "4",
    "nameDay" : "Четвер",
    "lessonNumber" : "3",
    "numberWeek" : "2",
    "timeStartLesson" : "14:15",
    "timeEndLesson" : "15:50",
    "lessonName" : "Архітектура комп'ютерів-1. Арифметичні та управляючі пристрої",
    "teacherName" : "проф. Жабін В. І.",
    "roomNumber" : "303-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64444,
    "dayNumber" : "4",
    "nameDay" : "Четвер",
    "lessonNumber" : "5",
    "numberWeek" : "2",
    "timeStartLesson" : "14:15",
    "timeEndLesson" : "15:50",
    "lessonName" : "Іноземна мова загальнотехнічного спрямування",
    "teacherName" : "",
    "roomNumber" : "712-07",
    "roomType" : "Прак"
  }, {
    "lessonID" : 64445,
    "dayNumber" : "5",
    "nameDay" : "П’ятниця",
    "lessonNumber" : "1",
    "numberWeek" : "1",
    "timeStartLesson" : "16:10",
    "timeEndLesson" : "17:45",
    "lessonName" : "Додаткові розділи теорії електричних і магнітних кіл",
    "teacherName" : "ас. Лободзинський В. Ю.",
    "roomNumber" : "305-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64446,
    "dayNumber" : "5",
    "nameDay" : "П’ятниця",
    "lessonNumber" : "2",
    "numberWeek" : "1",
    "timeStartLesson" : "16:10",
    "timeEndLesson" : "17:45",
    "lessonName" : "Системне програмування-1",
    "teacherName" : "ст.вик. Порєв В. М.",
    "roomNumber" : "303-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64447,
    "dayNumber" : "5",
    "nameDay" : "П’ятниця",
    "lessonNumber" : "3",
    "numberWeek" : "1",
    "timeStartLesson" : "16:10",
    "timeEndLesson" : "17:45",
    "lessonName" : "Додаткові розділи теорії електричних і магнітних кіл",
    "teacherName" : "ас. Лободзинський В. Ю.",
    "roomNumber" : null,
    "roomType" : null
  }, {
    "lessonID" : 64448,
    "dayNumber" : "5",
    "nameDay" : "П’ятниця",
    "lessonNumber" : "2",
    "numberWeek" : "2",
    "timeStartLesson" : "16:10",
    "timeEndLesson" : "17:45",
    "lessonName" : "Системне програмування-1",
    "teacherName" : "ст.вик. Порєв В. М.",
    "roomNumber" : "303-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64449,
    "dayNumber" : "5",
    "nameDay" : "П’ятниця",
    "lessonNumber" : "3",
    "numberWeek" : "2",
    "timeStartLesson" : "16:10",
    "timeEndLesson" : "17:45",
    "lessonName" : "Додаткові розділи теорії електричних і магнітних кіл",
    "teacherName" : "ас. Лободзинський В. Ю.",
    "roomNumber" : null,
    "roomType" : null
  } ]
}
```

**Errors**:

- Группа с таким id не найдена

```json
{
  "type" : "Not Found",
  "code" : 404,
  "message" : "Group not found"
}
```

#### 	Получить пары по имени группы

**URL**: `/getGroupAndLessonsByName/{name}`

**Request type**: `GET`

**Server successful answer**: 

```json
{
  "groupId" : 467,
  "groupName" : "ІВ-82",
  "groupUrl" : "http://rozklad.kpi.ua/Schedules/ViewSchedule.aspx?g=da7cc0da-31bb-4729-a727-14fe4b9a9851",
  "lessons" : [ {
    "lessonID" : 64421,
    "dayNumber" : "1",
    "nameDay" : "Понеділок",
    "lessonNumber" : "3",
    "numberWeek" : "1",
    "timeStartLesson" : "08:30",
    "timeEndLesson" : "10:05",
    "lessonName" : "Алгоритми та методи обчислень",
    "teacherName" : "проф. Новотарський М. А.",
    "roomNumber" : "302-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64422,
    "dayNumber" : "1",
    "nameDay" : "Понеділок",
    "lessonNumber" : "4",
    "numberWeek" : "1",
    "timeStartLesson" : "08:30",
    "timeEndLesson" : "10:05",
    "lessonName" : "Стратег. охор. навкол. середовища",
    "teacherName" : "ст.вик. Радовенчик Я. В.",
    "roomNumber" : "302-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64423,
    "dayNumber" : "1",
    "nameDay" : "Понеділок",
    "lessonNumber" : "1",
    "numberWeek" : "2",
    "timeStartLesson" : "08:30",
    "timeEndLesson" : "10:05",
    "lessonName" : "Інженерія програмного забезпечення-3. Проектування програмного забезпечення",
    "teacherName" : "доц. Болдак А. О.",
    "roomNumber" : "111-18",
    "roomType" : "Лаб"
  }, {
    "lessonID" : 64424,
    "dayNumber" : "1",
    "nameDay" : "Понеділок",
    "lessonNumber" : "2",
    "numberWeek" : "2",
    "timeStartLesson" : "08:30",
    "timeEndLesson" : "10:05",
    "lessonName" : "Інженерія програмного забезпечення-3. Проектування програмного забезпечення",
    "teacherName" : "доц. Болдак А. О.",
    "roomNumber" : "302-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64425,
    "dayNumber" : "1",
    "nameDay" : "Понеділок",
    "lessonNumber" : "3",
    "numberWeek" : "2",
    "timeStartLesson" : "08:30",
    "timeEndLesson" : "10:05",
    "lessonName" : "Алгоритми та методи обчислень",
    "teacherName" : "проф. Новотарський М. А.",
    "roomNumber" : "302-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64426,
    "dayNumber" : "2",
    "nameDay" : "Вівторок",
    "lessonNumber" : "1",
    "numberWeek" : "1",
    "timeStartLesson" : "10:25",
    "timeEndLesson" : "12:00",
    "lessonName" : "Стратег. охор. навкол. середовища",
    "teacherName" : "ст.вик. Радовенчик Я. В.",
    "roomNumber" : "231-18",
    "roomType" : "Прак"
  }, {
    "lessonID" : 64427,
    "dayNumber" : "2",
    "nameDay" : "Вівторок",
    "lessonNumber" : "2",
    "numberWeek" : "1",
    "timeStartLesson" : "10:25",
    "timeEndLesson" : "12:00",
    "lessonName" : "Методи оптимізації та планування експерименту",
    "teacherName" : "доц. Селіванов В. Л.",
    "roomNumber" : "301-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64428,
    "dayNumber" : "2",
    "nameDay" : "Вівторок",
    "lessonNumber" : "3",
    "numberWeek" : "1",
    "timeStartLesson" : "10:25",
    "timeEndLesson" : "12:00",
    "lessonName" : "Фізичне виховання",
    "teacherName" : null,
    "roomNumber" : null,
    "roomType" : null
  }, {
    "lessonID" : 64429,
    "dayNumber" : "2",
    "nameDay" : "Вівторок",
    "lessonNumber" : "2",
    "numberWeek" : "2",
    "timeStartLesson" : "10:25",
    "timeEndLesson" : "12:00",
    "lessonName" : "Методи оптимізації та планування експерименту",
    "teacherName" : "доц. Селіванов В. Л.",
    "roomNumber" : "301-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64430,
    "dayNumber" : "2",
    "nameDay" : "Вівторок",
    "lessonNumber" : "3",
    "numberWeek" : "2",
    "timeStartLesson" : "10:25",
    "timeEndLesson" : "12:00",
    "lessonName" : "Фізичне виховання",
    "teacherName" : null,
    "roomNumber" : null,
    "roomType" : null
  }, {
    "lessonID" : 64431,
    "dayNumber" : "3",
    "nameDay" : "Середа",
    "lessonNumber" : "2",
    "numberWeek" : "1",
    "timeStartLesson" : "12:20",
    "timeEndLesson" : "13:55",
    "lessonName" : "Комп'ютерна електроніка",
    "teacherName" : "ст.вик. Виноградов Ю. М.",
    "roomNumber" : "301-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64432,
    "dayNumber" : "3",
    "nameDay" : "Середа",
    "lessonNumber" : "3",
    "numberWeek" : "1",
    "timeStartLesson" : "12:20",
    "timeEndLesson" : "13:55",
    "lessonName" : "Комп'ютерна електроніка",
    "teacherName" : "ст.вик. Виноградов Ю. М.",
    "roomNumber" : "514-18",
    "roomType" : "Лаб"
  }, {
    "lessonID" : 64433,
    "dayNumber" : "3",
    "nameDay" : "Середа",
    "lessonNumber" : "4",
    "numberWeek" : "1",
    "timeStartLesson" : "12:20",
    "timeEndLesson" : "13:55",
    "lessonName" : "Архітектура комп'ютерів-1. Арифметичні та управляючі пристрої",
    "teacherName" : "доц. Верба О. А.",
    "roomNumber" : "535-18",
    "roomType" : "Лаб"
  }, {
    "lessonID" : 64434,
    "dayNumber" : "3",
    "nameDay" : "Середа",
    "lessonNumber" : "2",
    "numberWeek" : "2",
    "timeStartLesson" : "12:20",
    "timeEndLesson" : "13:55",
    "lessonName" : "Комп'ютерна електроніка",
    "teacherName" : "ст.вик. Виноградов Ю. М.",
    "roomNumber" : "301-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64435,
    "dayNumber" : "3",
    "nameDay" : "Середа",
    "lessonNumber" : "3",
    "numberWeek" : "2",
    "timeStartLesson" : "12:20",
    "timeEndLesson" : "13:55",
    "lessonName" : "Методи оптимізації та планування експерименту",
    "teacherName" : "ас. Регіда П. Г.",
    "roomNumber" : "513-18",
    "roomType" : "Лаб"
  }, {
    "lessonID" : 64436,
    "dayNumber" : "3",
    "nameDay" : "Середа",
    "lessonNumber" : "4",
    "numberWeek" : "2",
    "timeStartLesson" : "12:20",
    "timeEndLesson" : "13:55",
    "lessonName" : "Архітектура комп'ютерів-1. Арифметичні та управляючі пристрої",
    "teacherName" : "доц. Верба О. А.",
    "roomNumber" : "535-18",
    "roomType" : "Лаб"
  }, {
    "lessonID" : 64437,
    "dayNumber" : "4",
    "nameDay" : "Четвер",
    "lessonNumber" : "1",
    "numberWeek" : "1",
    "timeStartLesson" : "14:15",
    "timeEndLesson" : "15:50",
    "lessonName" : "Іноземна мова загальнотехнічного спрямування",
    "teacherName" : "вик. Сергеєва О. О.",
    "roomNumber" : "337-A-18",
    "roomType" : "Лаб"
  }, {
    "lessonID" : 64438,
    "dayNumber" : "4",
    "nameDay" : "Четвер",
    "lessonNumber" : "2",
    "numberWeek" : "1",
    "timeStartLesson" : "14:15",
    "timeEndLesson" : "15:50",
    "lessonName" : "Системне програмування-1",
    "teacherName" : "ст.вик. Порєв В. М.",
    "roomNumber" : "536-18",
    "roomType" : "Лаб"
  }, {
    "lessonID" : 64439,
    "dayNumber" : "4",
    "nameDay" : "Четвер",
    "lessonNumber" : "3",
    "numberWeek" : "1",
    "timeStartLesson" : "14:15",
    "timeEndLesson" : "15:50",
    "lessonName" : "Архітектура комп'ютерів-1. Арифметичні та управляючі пристрої",
    "teacherName" : "проф. Жабін В. І.",
    "roomNumber" : "303-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64440,
    "dayNumber" : "4",
    "nameDay" : "Четвер",
    "lessonNumber" : "5",
    "numberWeek" : "1",
    "timeStartLesson" : "14:15",
    "timeEndLesson" : "15:50",
    "lessonName" : "Іноземна мова загальнотехнічного спрямування",
    "teacherName" : "",
    "roomNumber" : "712-07",
    "roomType" : "Прак"
  }, {
    "lessonID" : 64441,
    "dayNumber" : "4",
    "nameDay" : "Четвер",
    "lessonNumber" : "1",
    "numberWeek" : "2",
    "timeStartLesson" : "14:15",
    "timeEndLesson" : "15:50",
    "lessonName" : "Іноземна мова загальнотехнічного спрямування",
    "teacherName" : "вик. Сергеєва О. О.",
    "roomNumber" : "337-A-18",
    "roomType" : "Лаб"
  }, {
    "lessonID" : 64442,
    "dayNumber" : "4",
    "nameDay" : "Четвер",
    "lessonNumber" : "2",
    "numberWeek" : "2",
    "timeStartLesson" : "14:15",
    "timeEndLesson" : "15:50",
    "lessonName" : "Алгоритми та методи обчислень",
    "teacherName" : "ст.вик. Порєв В. М.",
    "roomNumber" : "536-18",
    "roomType" : "Лаб"
  }, {
    "lessonID" : 64443,
    "dayNumber" : "4",
    "nameDay" : "Четвер",
    "lessonNumber" : "3",
    "numberWeek" : "2",
    "timeStartLesson" : "14:15",
    "timeEndLesson" : "15:50",
    "lessonName" : "Архітектура комп'ютерів-1. Арифметичні та управляючі пристрої",
    "teacherName" : "проф. Жабін В. І.",
    "roomNumber" : "303-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64444,
    "dayNumber" : "4",
    "nameDay" : "Четвер",
    "lessonNumber" : "5",
    "numberWeek" : "2",
    "timeStartLesson" : "14:15",
    "timeEndLesson" : "15:50",
    "lessonName" : "Іноземна мова загальнотехнічного спрямування",
    "teacherName" : "",
    "roomNumber" : "712-07",
    "roomType" : "Прак"
  }, {
    "lessonID" : 64445,
    "dayNumber" : "5",
    "nameDay" : "П’ятниця",
    "lessonNumber" : "1",
    "numberWeek" : "1",
    "timeStartLesson" : "16:10",
    "timeEndLesson" : "17:45",
    "lessonName" : "Додаткові розділи теорії електричних і магнітних кіл",
    "teacherName" : "ас. Лободзинський В. Ю.",
    "roomNumber" : "305-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64446,
    "dayNumber" : "5",
    "nameDay" : "П’ятниця",
    "lessonNumber" : "2",
    "numberWeek" : "1",
    "timeStartLesson" : "16:10",
    "timeEndLesson" : "17:45",
    "lessonName" : "Системне програмування-1",
    "teacherName" : "ст.вик. Порєв В. М.",
    "roomNumber" : "303-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64447,
    "dayNumber" : "5",
    "nameDay" : "П’ятниця",
    "lessonNumber" : "3",
    "numberWeek" : "1",
    "timeStartLesson" : "16:10",
    "timeEndLesson" : "17:45",
    "lessonName" : "Додаткові розділи теорії електричних і магнітних кіл",
    "teacherName" : "ас. Лободзинський В. Ю.",
    "roomNumber" : null,
    "roomType" : null
  }, {
    "lessonID" : 64448,
    "dayNumber" : "5",
    "nameDay" : "П’ятниця",
    "lessonNumber" : "2",
    "numberWeek" : "2",
    "timeStartLesson" : "16:10",
    "timeEndLesson" : "17:45",
    "lessonName" : "Системне програмування-1",
    "teacherName" : "ст.вик. Порєв В. М.",
    "roomNumber" : "303-18",
    "roomType" : "Лек"
  }, {
    "lessonID" : 64449,
    "dayNumber" : "5",
    "nameDay" : "П’ятниця",
    "lessonNumber" : "3",
    "numberWeek" : "2",
    "timeStartLesson" : "16:10",
    "timeEndLesson" : "17:45",
    "lessonName" : "Додаткові розділи теорії електричних і магнітних кіл",
    "teacherName" : "ас. Лободзинський В. Ю.",
    "roomNumber" : null,
    "roomType" : null
  } ]
}
```

**Errors**:

- Группа с таким именем не найдена

```json
{
  "type" : "Not Found",
  "code" : 404,
  "message" : "Group not found"
}
```

#### 	Получить пары по имени группы

**URL**: `/getAllGroups`

**Request type**: `GET`

**Server successful answer**: 

```json

[ {
  "groupId" : 1,
  "groupName" : "АК-91мп",
  "groupUrl" : "http://rozklad.kpi.ua/Schedules/ViewSchedule.aspx?g=2e237784-c64f-45c4-b8a5-edb314bc846a"
}, {
  "groupId" : 2,
  "groupName" : "АЛ-91",
  "groupUrl" : "http://rozklad.kpi.ua/Schedules/ViewSchedule.aspx?g=6356c937-5030-4b78-a7ac-1590a7eb5638"
}, {
  "groupId" : 3,
  "groupName" : "АЛ-91мп",
  "groupUrl" : "http://rozklad.kpi.ua/Schedules/ViewSchedule.aspx?g=f9542993-e14b-4676-b2f0-abf4a20cd5a9"
}, {
  "groupId" : 4,
  "groupName" : "АЛ-92",
  "groupUrl" : "http://rozklad.kpi.ua/Schedules/ViewSchedule.aspx?g=f2ba6171-951a-40d0-a7a5-7cbeadaed60c"
}, {
  "groupId" : 5,
  "groupName" : "АЛ-з91",
  "groupUrl" : "http://rozklad.kpi.ua/Schedules/ViewSchedule.aspx?g=e98e40ce-8a54-450c-b7ef-51e9a841e5e6"
}, {
  "groupId" : 6,
  "groupName" : "АМ-61",
  "groupUrl" : "http://rozklad.kpi.ua/Schedules/ViewSchedule.aspx?g=81cbb331-0258-4260-b30d-da4a498d652d"
}, {
  "groupId" : 7,
  "groupName" : "АМ-71",
  "groupUrl" : "http://rozklad.kpi.ua/Schedules/ViewSchedule.aspx?g=5a9431b7-e446-4e1d-8776-6adfda108727"
}, {
  "groupId" : 8,
  "groupName" : "АМ-81",
  "groupUrl" : "http://rozklad.kpi.ua/Schedules/ViewSchedule.aspx?g=3db406df-01be-43de-a59a-9cb2e45e2eba"
}, {
  "groupId" : 9,
  "groupName" : "АМ-81мп",
  "groupUrl" : "http://rozklad.kpi.ua/Schedules/ViewSchedule.aspx?g=c7e28aee-452b-4e61-ae90-9c12031a0652"
}, {
  "groupId" : 10,
  "groupName" : "АМ-91",
  "groupUrl" : "http://rozklad.kpi.ua/Schedules/ViewSchedule.aspx?g=1eb7e974-f87d-48d6-9ff6-0da790871bf5"
}, ///
```


