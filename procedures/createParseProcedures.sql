CREATE OR REPLACE PROCEDURE ParseGroups()
    language plpgsql
as
$$
Begin
    CREATE EXTENSION IF NOT EXISTS "uuid-ossp";


    INSERT INTO "Groups" ("Id", "Group_Name")
    SELECT uuid_generate_v4(), group_name
    from group_info;
END;
$$
CREATE OR REPLACE PROCEDURE ParseSubjects()
    language plpgsql
as
$$
Begin

    CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

    INSERT INTO "Subjects" ("Id", "GroupId", "Name", "Teachers")
    SELECT uuid_generate_v4(),
           (SELECT "Id"
            FROM "Groups"
            WHERE "Groups"."Group_Name" =
                  (SELECT group_name FROM group_info WHERE group_id = lesson_info.group_id))
               as group_name,
           lesson_info.lesson_name,
           (SELECT string_agg(distinct l2.teacher_name::text || l2.teacher_name, ',')
            FROM lesson_info l2
            where l2.lesson_name = lesson_name
              AND l2.group_id = lesson_info.group_id)
    FROM lesson_info
    GROUP BY group_name, lesson_name, group_id;
END;
$$

CREATE OR REPLACE PROCEDURE ParseLessons()
    language plpgsql
as
$$
Begin

    CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

    INSERT INTO "Lessons" ("Id", "SubjectId", "Week", "DayOfWeek", "TimeStart", "Type")
    SELECT uuid_generate_v4(),
           CAST((SELECT "Subjects"."Id"
                 FROM "Subjects"
                 WHERE "Subjects"."Name" = lesson_info.lesson_name
                   and "Subjects"."GroupId" =
                       (SELECT "Groups"."Id"
                        FROM "Groups"
                        WHERE "Group_Name" =
                              (SELECT group_info.group_name
                               FROM group_info
                               WHERE group_id = lesson_info.group_id))) as uuid),
           CAST(lesson_info.number_week as int),
           CAST(lesson_info.day_number as int),
           lesson_info.time_start_lesson,
           lesson_info.room_type
    FROM lesson_info;
END;
$$