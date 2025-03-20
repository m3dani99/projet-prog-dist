const express = require("express");
const bodyParser = require("body-parser");
const cors = require("cors");
const db = require("./db"); // Import MySQL connection

const app = express();
const PORT = process.env.PORT || 5000;

app.use(cors());
app.use(bodyParser.json());

// Get all restaurants
app.get("/restaurants", async (req, res) => {
  try {
    const [rows] = await db.query("SELECT * FROM restaurants");
    res.json(rows);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

// Get a restaurant by ID
app.get("/restaurants/:id", async (req, res) => {
  try {
    const [rows] = await db.query("SELECT * FROM restaurants WHERE id = ?", [req.params.id]);
    if (rows.length === 0) return res.status(404).json({ error: "Restaurant not found" });
    res.json(rows[0]);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

// Create a new restaurant
app.post("/restaurants", async (req, res) => {
  const { name, location, capacity } = req.body;
  try {
    const [result] = await db.query(
      "INSERT INTO restaurants (name, location, capacity) VALUES (?, ?, ?)",
      [name, location, capacity]
    );
    res.status(201).json({ id: result.insertId, name, location, capacity });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

// Add availability to a restaurant
app.post("/restaurants/:id/availability", async (req, res) => {
  const { date, slots } = req.body;
  const restaurantId = parseInt(req.params.id, 10); 

  if (isNaN(restaurantId)) {
    return res.status(400).json({ error: "Invalid restaurant ID" });
  }

  try {
    const slotsJson = JSON.stringify(slots);

    await db.query(
      "INSERT INTO availabilities (restaurant_id, date, slots) VALUES (?, ?, ?)",
      [restaurantId, date, slotsJson]
    );

    res.status(201).json({ restaurant_id: restaurantId, date, slots });
  } catch (error) {
    console.error("Database error:", error);
    res.status(500).json({ error: error.message });
  }
});


// Get availability of a restaurant
app.get("/restaurants/:id/availability", async (req, res) => {
  try {
    const [rows] = await db.query(
      "SELECT * FROM availabilities WHERE restaurant_id = ?",
      [req.params.id]
    );
    res.json(rows);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

// Delete a restaurant
app.delete("/restaurants/:id", async (req, res) => {
  try {
    await db.query("DELETE FROM restaurants WHERE id = ?", [req.params.id]);
    res.status(204).send();
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

// Start the server
app.listen(PORT, () => {
  console.log(`Restaurant service running on port ${PORT}`);
});
