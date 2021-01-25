CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO "Subjects" ("Id", "GroupId", "Name", "Teachers")
SELECT uuid_generate_v4(),
       (SELECT "Id"
        FROM "Groups"
        WHERE "Groups"."Group_Name" =
              (SELECT group_name FROM group_info WHERE group_id = lesson_info.group_id))
        ,
       lesson_info.lesson_name,
       lesson_info.teacher_name
FROM lesson_info
ORDER BY lesson_name