CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO "Lessons" ("Id", "SubjectId", "Week", "DayOfWeek", "TimeStart", "Type")
SELECT uuid_generate_v4(),
       (SELECT "Subjects"."Id" FROM "Subjects" WHERE "Subjects"."Name" = lesson_info.lesson_name),
       CAST(lesson_info.number_week as int) ,
       CAST(lesson_info.day_number as int),
       lesson_info.time_start_lesson,
       lesson_info.room_type
FROM lesson_info