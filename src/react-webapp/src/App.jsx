import {useState} from 'react'
import viteLogo from '/n11.svg'
import './App.css'
import {createTalk} from "./services/TalkService.jsx";
import PresentationApply from "./components/PresentationApply.jsx";

function App() {
    const [page, setPage] = useState('')
    const [meetingSubject, setMeetingSubject] = useState('')
    const [meetingDuration, setMeetingDuration] = useState(30)

    const handleApplyButtonClick = () => {
        createTalk(meetingSubject, meetingDuration).then(r => console.log('başarılı'))
    }

    return (
        <>
            <div>
                <div className="navbar">
                    <div style={{display: "flex", justifyContent: "center"}}>
                        <p className="menu" onClick={() => setPage('presentationApply')}>Presentation Apply</p>
                        <p className="menu" style={{paddingLeft: "1em"}}>|</p>
                        <p className="menu" onClick={() => setPage('conferenceDetail')}
                           style={{paddingLeft: "1em"}}>Conference Detail</p>
                    </div>
                    <img src={viteLogo} className="logo" alt="n11 logo"/>
                </div>
                {page === 'presentationApply' && <PresentationApply/>}
            </div>
        </>
    )
}

export default App
