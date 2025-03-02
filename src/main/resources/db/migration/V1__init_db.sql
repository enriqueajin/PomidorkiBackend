DROP TABLE IF EXISTS "users";
CREATE TABLE "users" (
    user_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    display_name TEXT,
    email TEXT NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    streak_count INTEGER NOT NULL DEFAULT 0,
    last_pomodoro_date TIMESTAMPTZ,
    avatar_url TEXT,
    is_active BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

DROP TABLE IF EXISTS "categories";
CREATE TABLE "categories" (
    user_id BIGINT NOT NULL,
    category_name TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    PRIMARY KEY (user_id, category_name),
    FOREIGN KEY (user_id) REFERENCES "users"(user_id)
);

DROP TABLE IF EXISTS "status";
CREATE TABLE "status" (
    status_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name TEXT NOT NULL UNIQUE
);

DROP TABLE IF EXISTS "priorities";
CREATE TABLE "priorities" (
    priority_id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name TEXT NOT NULL UNIQUE
);

DROP TABLE IF EXISTS "tasks";
CREATE TABLE "tasks" (
    task_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT NOT NULL,
    category_name TEXT NOT NULL,
    title TEXT NOT NULL,
    description TEXT,
    due_date DATE NOT NULL,
    status_id INTEGER NOT NULL,
    target_pomodoros INTEGER NOT NULL,
    priority_id INTEGER NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  	FOREIGN KEY (user_id) REFERENCES "users"(user_id),
    FOREIGN KEY (category_name, user_id) REFERENCES "categories"(category_name, user_id),
    FOREIGN KEY (status_id) REFERENCES "status"(status_id),
    FOREIGN KEY (priority_id) REFERENCES "priorities"(priority_id)
);

DROP TABLE IF EXISTS "pomodoros";
CREATE TABLE "pomodoros" (
    pomodoro_id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id BIGINT NOT NULL,
    task_id BIGINT NOT NULL,
    ended_at TIMESTAMPTZ NOT NULL,
    started_at TIMESTAMPTZ NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "users"(user_id),
    FOREIGN KEY (task_id) REFERENCES "tasks"(task_id)
);