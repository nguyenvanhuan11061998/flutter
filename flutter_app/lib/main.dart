import 'package:flutter/material.dart';

void main() {
  runApp(new MaterialApp(
    home: new SoLonNhat()
  ));
}

class SoLonNhat extends StatefulWidget {
  @override
  SoLonNhatState createState() => new SoLonNhatState();


}

class SoLonNhatState extends State<SoLonNhat>{

  String soLonNhat = "";

  String _so1 ="";
  String _so2 = "";
  String _so3 = "";
  String _so4 = "";
  double _max = 0;
  

  _buttonPressed(String so1, String so2, String so3, String so4){

    _max = double.parse(so1);
    double _ptu2 = double.parse(so2);
    double _ptu3 = double.parse(so3);
    double _ptu4 = double.parse(so4);

    if(_max < _ptu2){
      _max = _ptu2;
    }

    if(_max < _ptu3){
      _max = _ptu3;
    }

    if(_max < _ptu4){
      _max = _ptu4;
    }
    setState(() {
      soLonNhat = _max.toString();
    });
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(title: new Text("tìm số lớn nhất"),backgroundColor: Colors.blueAccent,),
      body: new Container(

        child: new Center(
          child: new Column(
            children: <Widget>[

              Padding (
                padding: EdgeInsets.only(top: 30),
                   child: (Text("nhập số thứ nhất")),
              ),

              new TextField(
                decoration: new InputDecoration(
                  hintText: "nhập số "
                ),
                onChanged: (String str1){
                  setState(() {
                    _so1 = str1;
                  });
                },
              ),

              Padding (
                padding: EdgeInsets.only(top: 30),
                child: (Text("nhập số thứ hai")),
              ),

              new TextField(
                decoration: new InputDecoration(
                    hintText: "nhập số "
                ),
                onChanged: (String str2){
                  setState(() {

                    _so2 = str2;
                  });
                },
              ),

              Padding (
                padding: EdgeInsets.only(top: 30),
                child: (Text("nhập số thứ ba")),
              ),

              new TextField(
                decoration: new InputDecoration(
                    hintText: "nhập số "
                ),
                onChanged: (String str3){
                  setState(() {
                    _so3 = str3;
                  });
                },
              ),

              Padding (
                padding: EdgeInsets.only(top: 30),
                child: (Text("nhập số thứ tư")),
              ),

              new TextField(
                decoration: new InputDecoration(
                    hintText: "nhập số "
                ),
                onChanged: (String str4){
                  setState(() {

                    _so4 = str4;
                  });
                },
              ),

              Padding(
                padding:EdgeInsets.only(top: 60),
              ),

              new MaterialButton(
                child: Text('Số lớn nhất: '),
                textColor: Colors.black,
                onPressed: () => _buttonPressed(_so1, _so2, _so3, _so4),

                color: Colors.amber,

              ),

              Padding(
                padding:EdgeInsets.only(top: 30),
              ),

              new Text(soLonNhat),

            ],
          ),
        ),
      ),
    );
  }
}

