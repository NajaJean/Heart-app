function myFunction(timestamp, ECGarray) {
    const oldTimeStamp = new Date(timestamp);
    var ecg = {
        time: [],
        ecg: []
    };
    var i;
    for (i = 0; i < ECGarray.length; i++) {
        newTimeStamp = new Date(oldTimeStamp + 86400000 * 1/ECGarray.length * i);
        ecg.time.push(newTimeStamp);
        ecg.ecg.push(ECGarray[i]);
    }
    return ecg; 
}