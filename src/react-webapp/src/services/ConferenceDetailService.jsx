import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/conference';

export const findAll = async () => {
    try {
        const response = await axios.get(`${BASE_URL}/findAll`);
        return response.data;
    } catch (error) {
        console.error('Hata:', error);
        throw error;
    }
};
