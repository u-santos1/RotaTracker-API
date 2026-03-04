-- 1. Tabela de Veículos
CREATE TABLE vehicles (
    id BIGSERIAL PRIMARY KEY,
    plate VARCHAR(20) NOT NULL UNIQUE,
    model VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE -- Corrigido: NOT NULL DEFAULT
);

-- 2. Tabela de Entregadores (Drivers)
CREATE TABLE drivers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL, -- Entregador precisa de nome
    cnh VARCHAR(20) NOT NULL UNIQUE, -- Entregador precisa de CNH
    active BOOLEAN NOT NULL DEFAULT TRUE
);

-- 3. Tabela de Rotas (Aqui entram as chaves estrangeiras)
CREATE TABLE routes (
    id BIGSERIAL PRIMARY KEY,
    origin VARCHAR(255) NOT NULL,
    destination VARCHAR(255) NOT NULL,
    status VARCHAR(50) NOT NULL,
    driver_id BIGINT NOT NULL,
    vehicle_id BIGINT NOT NULL,

    -- As chaves estrangeiras ligam a Rota ao Motorista e ao Veículo
    CONSTRAINT fk_routes_driver_id FOREIGN KEY (driver_id) REFERENCES drivers(id),
    CONSTRAINT fk_routes_vehicle_id FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
);