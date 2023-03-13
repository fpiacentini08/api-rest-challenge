CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE api_calls (
    id uuid DEFAULT uuid_generate_v4 (),
    data character varying(255) NOT NULL UNIQUE,
    created_at timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL
);
