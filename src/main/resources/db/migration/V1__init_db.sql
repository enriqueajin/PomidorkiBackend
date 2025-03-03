CREATE SEQUENCE IF NOT EXISTS user_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS status_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS priority_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS task_id_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE IF NOT EXISTS pomodoro_id_seq START WITH 1 INCREMENT BY 1;

DROP TABLE IF EXISTS "users";
CREATE TABLE "users" (
    "user_id" bigint NOT NULL,
    "display_name" VARCHAR(50),
    "email" VARCHAR(150) NOT NULL UNIQUE,
    "password_hash" VARCHAR(100) NOT NULL,
    "streak_count" INTEGER NOT NULL,
    "last_pomodoro_date" TIMESTAMP,
    "avatar_url" VARCHAR(255),
    "is_active" BOOLEAN NOT NULL,
    "created_at" TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP NOT NULL,
    CONSTRAINT "pk_users" PRIMARY KEY ("user_id")
);

DROP TABLE IF EXISTS "categories";
CREATE TABLE "categories" (
    "user_id" bigint NOT NULL,
    "category_name" VARCHAR(20) NOT NULL,
    "created_at" TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP NOT NULL,
    CONSTRAINT "pk_categories" PRIMARY KEY ("user_id", "category_name"),
    CONSTRAINT "fk_categories_user_id" FOREIGN KEY ("user_id") REFERENCES "users" ("user_id")
);

DROP TABLE IF EXISTS "status";
CREATE TABLE "status" (
    "status_id" smallint NOT NULL,
    "name" VARCHAR(15) NOT NULL UNIQUE,
    CONSTRAINT "pk_status" PRIMARY KEY ("status_id")
);

DROP TABLE IF EXISTS "priorities";
CREATE TABLE "priorities" (
    "priority_id" smallint NOT NULL,
    "name" VARCHAR(15) NOT NULL UNIQUE,
    CONSTRAINT "pk_priorities" PRIMARY KEY ("priority_id")
);

DROP TABLE IF EXISTS "tasks";
CREATE TABLE "tasks" (
    "task_id" bigint NOT NULL,
    "user_id" bigint NOT NULL,
    "category_name" VARCHAR(20) NOT NULL,
    "title" VARCHAR(100) NOT NULL,
    "description" VARCHAR(255),
    "due_date" TIMESTAMP NOT NULL,
    "status_id" smallint NOT NULL,
    "target_pomodoros" smallint NOT NULL,
    "priority_id" smallint NOT NULL,
    "created_at" TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP NOT NULL,
    CONSTRAINT "pk_tasks" PRIMARY KEY ("task_id"),
    CONSTRAINT "fk_tasks_user_id" FOREIGN KEY ("user_id") REFERENCES "users" ("user_id"),
    CONSTRAINT "fk_tasks_status_id" FOREIGN KEY ("status_id") REFERENCES "status" ("status_id"),
    CONSTRAINT "fk_tasks_priorities_id" FOREIGN KEY ("priority_id") REFERENCES "priorities" ("priority_id"),
    CONSTRAINT "fk_tasks_user_id_category_name" FOREIGN KEY ("user_id", "category_name") REFERENCES "categories" ("user_id", "category_name")
);

DROP TABLE IF EXISTS "pomodoros";
CREATE TABLE "pomodoros" (
    "pomodoro_id" bigint NOT NULL,
    "user_id" bigint NOT NULL,
    "task_id" bigint NOT NULL,
    "ended_at" TIMESTAMP NOT NULL,
    "started_at" TIMESTAMP NOT NULL,
    CONSTRAINT "pk_pomodoros" PRIMARY KEY ("pomodoro_id"),
    CONSTRAINT "fk_pomodoros_user_id" FOREIGN KEY ("user_id") REFERENCES "users" ("user_id"),
    CONSTRAINT "fk_pomodoros_task_id" FOREIGN KEY ("task_id") REFERENCES "tasks" ("task_id")
);