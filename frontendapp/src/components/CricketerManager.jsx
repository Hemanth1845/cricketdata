    import React, { useState, useEffect } from 'react';
    import axios from 'axios';
    import './style.css';
    import config from './config.js';

    const CricketerManager = () => {
    const [cricketers, setCricketers] = useState([]);
    const [cricketer, setCricketer] = useState({
        id: '',
        name: '',
        age: '',
        role: '',
        team: '',
        battingAverage: '',
        bowlingAverage: '',
        matchesPlayed: ''
    });
    const [idToFetch, setIdToFetch] = useState('');
    const [fetchedCricketer, setFetchedCricketer] = useState(null);
    const [message, setMessage] = useState('');
    const [editMode, setEditMode] = useState(false);

    const baseUrl = `${config.url}/cricketerapi`;

    useEffect(() => {
        fetchAllCricketers();
    }, []);

    const fetchAllCricketers = async () => {
        try {
        const res = await axios.get(`${baseUrl}/all`);
        setCricketers(res.data);
        } catch (error) {
        setMessage('Failed to fetch cricketers.');
        }
    };

    const handleChange = (e) => {
        setCricketer({ ...cricketer, [e.target.name]: e.target.value });
    };

    const validateForm = () => {
        for (let key in cricketer) {
        if (!cricketer[key] || cricketer[key].toString().trim() === '') {
            setMessage(`Please fill out the ${key} field.`);
            return false;
        }
        }
        return true;
    };

    const addCricketer = async () => {
        if (!validateForm()) return;
        try {
        await axios.post(`${baseUrl}/add`, cricketer);
        setMessage('Cricketer added successfully.');
        fetchAllCricketers();
        resetForm();
        } catch (error) {
        setMessage('Error adding cricketer.');
        }
    };

    const updateCricketer = async () => {
        if (!validateForm()) return;
        try {
        await axios.put(`${baseUrl}/update`, cricketer);
        setMessage('Cricketer updated successfully.');
        fetchAllCricketers();
        resetForm();
        } catch (error) {
        setMessage('Error updating cricketer.');
        }
    };

    const deleteCricketer = async (id) => {
        try {
        const res = await axios.delete(`${baseUrl}/delete/${id}`);
        setMessage(res.data);
        fetchAllCricketers();
        } catch (error) {
        setMessage('Error deleting cricketer.');
        }
    };

    const getCricketerById = async () => {
        try {
        const res = await axios.get(`${baseUrl}/get/${idToFetch}`);
        setFetchedCricketer(res.data);
        setMessage('');
        } catch (error) {
        setFetchedCricketer(null);
        setMessage('Cricketer not found.');
        }
    };

    const handleEdit = (cricketer) => {
        setCricketer(cricketer);
        setEditMode(true);
        setMessage(`Editing cricketer with ID ${cricketer.id}`);
    };

    const resetForm = () => {
        setCricketer({
        id: '',
        name: '',
        age: '',
        role: '',
        team: '',
        battingAverage: '',
        bowlingAverage: '',
        matchesPlayed: ''
        });
        setEditMode(false);
    };

    return (
        <div className="student-container">
        {message && (
            <div className={`message-banner ${message.toLowerCase().includes('error') ? 'error' : 'success'}`}>
            {message}
            </div>
        )}

        <h2>Cricket Management System</h2>

        <div>
            <h3>{editMode ? 'Edit Cricketer' : 'Add Cricketer'}</h3>
            <div className="form-grid">
            <input type="number" name="id" placeholder="ID" value={cricketer.id} onChange={handleChange} />
            <input type="text" name="name" placeholder="Name" value={cricketer.name} onChange={handleChange} />
            <input type="number" name="age" placeholder="Age" value={cricketer.age} onChange={handleChange} />
            <select name="role" value={cricketer.role} onChange={handleChange}>
                <option value="">Select Role</option>
                <option value="Batsman">Batsman</option>
                <option value="Bowler">Bowler</option>
                <option value="All-rounder">All-rounder</option>
                <option value="Wicket-keeper">Wicket-keeper</option>
            </select>
            <input type="text" name="team" placeholder="Team" value={cricketer.team} onChange={handleChange} />
            <input type="number" step="0.01" name="battingAverage" placeholder="Batting Average" value={cricketer.battingAverage} onChange={handleChange} />
            <input type="number" step="0.01" name="bowlingAverage" placeholder="Bowling Average" value={cricketer.bowlingAverage} onChange={handleChange} />
            <input type="number" name="matchesPlayed" placeholder="Matches Played" value={cricketer.matchesPlayed} onChange={handleChange} />
            </div>

            <div className="btn-group">
            {!editMode ? (
                <button className="btn-blue" onClick={addCricketer}>Add Cricketer</button>
            ) : (
                <>
                <button className="btn-green" onClick={updateCricketer}>Update Cricketer</button>
                <button className="btn-gray" onClick={resetForm}>Cancel</button>
                </>
            )}
            </div>
        </div>

        <div>
            <h3>Get Cricketer By ID</h3>
            <input
            type="number"
            value={idToFetch}
            onChange={(e) => setIdToFetch(e.target.value)}
            placeholder="Enter ID"
            />
            <button className="btn-blue" onClick={getCricketerById}>Fetch</button>

        {fetchedCricketer && (
    <div className="fetched-table">
        <h4>Cricketer Found:</h4>
        <table className="single-cricketer-table">
        <tbody>
            {Object.entries(fetchedCricketer).map(([key, value]) => (
            <tr key={key}>
                <th>{key}</th>
                <td>{value}</td>
            </tr>
            ))}
        </tbody>
        </table>
    </div>
    )}

        </div>

        <div>
            <h3>All Cricketers</h3>
            {cricketers.length === 0 ? (
            <p>No cricketers found.</p>
            ) : (
            <div className="table-wrapper">
                <table>
                <thead>
                    <tr>
                    {Object.keys(cricketer).map((key) => (
                        <th key={key}>{key}</th>
                    ))}
                    <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {cricketers.map((cricketer) => (
                    <tr key={cricketer.id}>
                        {Object.keys(cricketer).map((key) => (
                        <td key={key}>{cricketer[key]}</td>
                        ))}
                        <td>
                        <div className="action-buttons">
                            <button className="btn-green" onClick={() => handleEdit(cricketer)}>Edit</button>
                            <button className="btn-red" onClick={() => deleteCricketer(cricketer.id)}>Delete</button>
                        </div>
                        </td>
                    </tr>
                    ))}
                </tbody>
                </table>
            </div>
            )}
        </div>
        </div>
    );
    };

    export default CricketerManager;