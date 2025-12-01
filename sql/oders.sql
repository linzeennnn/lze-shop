-- 订单表
CREATE TABLE orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT,
    total_amount DECIMAL(10,2),
    status VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY(user_id) REFERENCES user(id)
) ENGINE=InnoDB;

-- 订单详情表
CREATE TABLE orders_item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT,
    goods_id BIGINT,
    quantity INT,
    price DECIMAL(10,2),
    FOREIGN KEY(order_id) REFERENCES orders(id),
    FOREIGN KEY(goods_id) REFERENCES goods(id)
) ENGINE=InnoDB;

-- 物流跟踪
CREATE TABLE orders_logistics (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    orders_item_id BIGINT,
    status VARCHAR(50),
    location VARCHAR(255),
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(orders_item_id) REFERENCES orders_item(id)
) ENGINE=InnoDB;

-- 秒杀/团购/特价
CREATE TABLE orders_promotion (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    goods_id BIGINT,
    type VARCHAR(20) COMMENT '秒杀, 团购, 特价',
    start_time DATETIME,
    end_time DATETIME,
    discount DECIMAL(10,2),
    FOREIGN KEY(goods_id) REFERENCES goods(id)
) ENGINE=InnoDB;

-- 订单售后信息表
CREATE TABLE orders_after_sales (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    order_id BIGINT,
    user_id BIGINT,
    issue TEXT,
    status VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(order_id) REFERENCES orders(id),
    FOREIGN KEY(user_id) REFERENCES user(id)
) ENGINE=InnoDB;
