const mysql = require("mysql2");

// Create a MySQL connection pool
const pool = mysql.createPool({
  host: "localhost",
  port: 3306, 
  user: "khalil",
  password: "pass123",
  database: "restaurant_booking",
  waitForConnections: true,
  connectionLimit: 10,
  queueLimit: 0
});

module.exports = pool.promise(); // Use promise-based queries
