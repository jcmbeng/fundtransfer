create table accounts
(
    id             bigint         not null,
    created_at     timestamp(6)   not null,
    is_deleted     boolean        not null,
    updated_at     timestamp(6),
    currency       varchar(3)     not null,
    account_status varchar(255)   not null check (account_status in ('ACTIVE', 'INACTIVE', 'SUSPENDED', 'CLOSED')),
    total_balance  numeric(19, 4) not null,
    usable_balance numeric(19, 4) not null,
    client_id      bigint         not null,
    primary key (id)
);

create table clients
(
    id           bigint       not null,
    created_at   timestamp(6) not null,
    is_deleted   boolean      not null,
    updated_at   timestamp(6),
    email        varchar(100) not null,
    name         varchar(100) not null,
    phone_number varchar(100) not null,
    primary key (id)
)