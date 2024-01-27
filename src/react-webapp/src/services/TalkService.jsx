import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/talk';

export const createTalk = async (meetingTitle, meetingDuration) => {
    try {
        const requestBody = {
            title: meetingTitle,
            duration: meetingDuration
        };

        const response = await axios.post(`${BASE_URL}/create`, requestBody);
        return response.data;
    } catch (error) {
        console.error('Hata:', error);
        throw error;
    }
};
