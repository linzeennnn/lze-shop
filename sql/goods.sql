-- 商品分类信息表
CREATE TABLE goods_category (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    parent_id BIGINT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 商品品牌信息表
CREATE TABLE goods_brand (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    logo VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 商品供应商信息表
CREATE TABLE goods_supplier (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    contact VARCHAR(50),
    phone VARCHAR(20),
    address VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 运费模板信息表
CREATE TABLE goods_shipping_template (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    template_name VARCHAR(100),
    base_fee DECIMAL(10,2),
    additional_fee DECIMAL(10,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 优惠券信息表
CREATE TABLE goods_coupon (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code VARCHAR(50) UNIQUE,
    discount DECIMAL(10,2),
    min_amount DECIMAL(10,2),
    start_time DATETIME,
    end_time DATETIME,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 商品信息表
CREATE TABLE goods (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    category_id BIGINT,
    brand_id BIGINT,
    supplier_id BIGINT,
    price DECIMAL(10,2),
    stock INT,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY(category_id) REFERENCES goods_category(id),
    FOREIGN KEY(brand_id) REFERENCES goods_brand(id),
    FOREIGN KEY(supplier_id) REFERENCES goods_supplier(id)
);

-- 商品评价表
CREATE TABLE goods_review (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    goods_id BIGINT,
    user_id BIGINT,
    rating TINYINT,
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(goods_id) REFERENCES goods(id),
    FOREIGN KEY(user_id) REFERENCES user(id)
);
