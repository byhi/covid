import { Container, Row, Col } from 'react-bootstrap';

import ComboBox from './components/ComboBox.js';
import './App.css';

function App() {
  return (
    
        <Container> 
            <Row>
              <Col md={4}>
                <h1 className="header">Welcome In Covid Status Site</h1>
              </Col> 
            </Row>
            <Row >
              <Col>
                  <ComboBox />
              </Col> 
            </Row>
        </Container> 
          
  );
}

export default App;


