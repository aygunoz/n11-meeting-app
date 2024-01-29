import {useState} from 'react'
import viteLogo from '/n11.svg'
import './App.css'
import {createTalk} from "./services/TalkService.jsx";
import PresentationApply from "./components/PresantationApply/PresentationApply.jsx";
import ConferenceDetails from "./components/ConferenceDetails/ConferenceDetails.jsx";
import tracks from './assets/json/tracks.json'
import {findAll} from "./services/ConferenceDetailService.jsx";

function App() {
    const [page, setPage] = useState('')
    const [tracks, setTracks] = useState([])
    const [meetingSubject, setMeetingSubject] = useState('')
    const [meetingDuration, setMeetingDuration] = useState(30)

    const handleApplyButtonClick = () => {
        createTalk(meetingSubject, meetingDuration).then(r => console.log('başarılı'))
    }
    const handleConferenceDetailClick = () => {
        findAll().then(r => {
            setTracks(r);
            setPage('conferenceDetail');
        });
    }

    return (
        <>
            <div>
                <div className="navbar">
                    <div style={{display: "flex", justifyContent: "center"}}>
                        <p className="menu" onClick={() => setPage('presentationApply')}>Presentation Apply</p>
                        <p className="menu" style={{paddingLeft: "1em"}}>|</p>
                        <p className="menu" onClick={handleConferenceDetailClick}
                           style={{paddingLeft: "1em"}}>Conference Detail</p>
                    </div>
                    <img src={viteLogo} className="logo" alt="n11 logo"/>
                </div>
                {page === 'presentationApply' && <PresentationApply/>}
                {page === 'conferenceDetail' && <ConferenceDetails tracks={tracks}/>}
            </div>
        </>
    )
}

export default App
