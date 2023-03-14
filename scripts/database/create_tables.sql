CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE api_call (
    id SERIAL PRIMARY KEY,
    data character varying(255) NOT NULL,
    created_at timestamp with time zone NOT NULL
);
