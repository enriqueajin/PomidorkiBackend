DROP SEQUENCE IF EXISTS "user_id_seq";
CREATE SEQUENCE "user_id_seq" INCREMENT BY 1 START 1;

DROP SEQUENCE IF EXISTS "status_id_seq";
CREATE SEQUENCE "status_id_seq" INCREMENT BY 1 START 1;

DROP SEQUENCE IF EXISTS "priority_id_seq";
CREATE SEQUENCE "priority_id_seq" INCREMENT BY 1 START 1;

DROP SEQUENCE IF EXISTS "task_id_seq";
CREATE SEQUENCE "task_id_seq" INCREMENT BY 1 START 1;

DROP SEQUENCE IF EXISTS "pomodoro_id_seq";
CREATE SEQUENCE "pomodoro_id_seq" INCREMENT BY 1 START 1;

DROP TABLE IF EXISTS "users";
CREATE TABLE "users" (
    "user_id" bigint NOT NULL,
    "display_name" VARCHAR(50),
    "email" TEXT NOT NULL UNIQUE,
    "password_hash" TEXT NOT NULL,
    "streak_count" INTEGER NOT NULL,
    "last_pomodoro_date" TIMESTAMP,
    "avatar_url" TEXT,
    "is_active" BOOLEAN NOT NULL,
    "created_at" TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP NOT NULL,
    CONSTRAINT "users_pkey" PRIMARY KEY ("user_id")
);

DROP TABLE IF EXISTS "categories";
CREATE TABLE "categories" (
    "user_id" bigint NOT NULL,
    "category_name" VARCHAR(20) NOT NULL,
    "created_at" TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP NOT NULL,
    CONSTRAINT "categories_pkey" PRIMARY KEY ("user_id", "category_name")
);

DROP TABLE IF EXISTS "status";
CREATE TABLE "status" (
    "status_id" smallint NOT NULL,
    "name" VARCHAR(15) NOT NULL UNIQUE,
    CONSTRAINT "status_pkey" PRIMARY KEY ("status_id")
);

DROP TABLE IF EXISTS "priorities";
CREATE TABLE "priorities" (
    "priority_id" smallint NOT NULL,
    "name" VARCHAR(15) NOT NULL UNIQUE,
    CONSTRAINT "priorities_pkey" PRIMARY KEY ("priority_id")

);

DROP TABLE IF EXISTS "tasks";
CREATE TABLE "tasks" (
    "task_id" bigint NOT NULL,
    "user_id" bigint NOT NULL,
    "category_name" VARCHAR(20) NOT NULL,
    "title" VARCHAR(100) NOT NULL,
    "description" TEXT,
    "due_date" TIMESTAMP NOT NULL,
    "status_id" smallint NOT NULL,
    "target_pomodoros" smallint NOT NULL,
    "priority_id" smallint NOT NULL,
    "created_at" TIMESTAMP NOT NULL,
    "updated_at" TIMESTAMP NOT NULL,
    CONSTRAINT "tasks_pkey" PRIMARY KEY ("task_id")

);

DROP TABLE IF EXISTS "pomodoros";
CREATE TABLE "pomodoros" (
    "pomodoro_id" bigint NOT NULL,
    "user_id" bigint NOT NULL,
    "task_id" bigint NOT NULL,
    "ended_at" TIMESTAMP NOT NULL,
    "started_at" TIMESTAMP NOT NULL,
    CONSTRAINT "pomodoros_pkey" PRIMARY KEY ("pomodoro_id")
);