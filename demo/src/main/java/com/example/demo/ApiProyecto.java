package com.example.demo;
import com.example.demo.Alumno;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;


@CrossOrigin
@RequestMapping("api")
@RestController
public class ApiProyecto {
    static ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
    @GetMapping ("/saludar")
    public String saludar(){

        return "Este es un demo en API";
    }

    @GetMapping("/llenar")
    public String llenar(){

        alumnos.add(new Alumno("2","Leonardo Cruz","Direccion 3","leo@gmail.com","plomero","6861234985","Soy trabajador","inteligente","Lic. en derecho", "23/7/2021"));

        return "todos llenos";
    }

    @GetMapping("/all")
    public ArrayList<Alumno> getAlumno(){
        return alumnos;
    }

    @GetMapping("/find/{id}")
    public Alumno getAlumnoById(@PathVariable("id") String id){
        Alumno alumno = null;
        for(Alumno a: alumnos){
            if(a.getId().equals(id)){
                alumno=a;
            }
        }
        return alumno;
    }
    @GetMapping("/enviar/{id}")
    public Alumno Enviar(@PathVariable("id") String id) {
        return getAlumnoById(id);
    }

        @PostMapping("/save")
    public Alumno insertarAlumno(@RequestBody Alumno alumno) throws Exception{
        if(getAlumnoById(alumno.getId())!=null)
            return alumno;
        alumnos.add(alumno);
        return alumnos.get(alumnos.size()-1);
    }

    @DeleteMapping("delete/{id}")
    public Alumno deleteAlumno(@PathVariable("id") String id){
        Alumno alumno = getAlumnoById(id);
        alumnos.remove(alumno);
        return alumno;
    }

    @PutMapping("/update/{id}/name/{name}")
    public Alumno updateNombrebyId(@PathVariable("id" ) String id, @PathVariable("name") String name){
        Alumno alumno = null;
        for(Alumno a: alumnos){
            if(a.getId()==id){
                a.setNombre(name);
                alumno=a;
            }
        }
        return alumno;
    }

    @PutMapping("/update")
    public Alumno updateAll(@RequestBody Alumno al){
        Alumno alumno = null;
        for(Alumno a: alumnos){
            if(a.getId()==al.getId()){
                a.setNombre(al.getNombre());
                a.setDireccion(al.getDireccion());
                a.setCorreo(al.getCorreo());
                alumno=a;
            }
        }
        return alumno;
    }
/*
    @PostMapping("/saveToFile")
    public String save(){
        try {
            FileWriter writer = new FileWriter("output.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            for (Alumno alumno : alumnos) {
                bufferedWriter.write(alumno.getId());
                bufferedWriter.write(",");
                bufferedWriter.write(alumno.getNombre());
                bufferedWriter.write(",");
                bufferedWriter.write(alumno.getDireccion());
                bufferedWriter.write(",");
                bufferedWriter.write(alumno.getEmail());
                bufferedWriter.write(",");
                bufferedWriter.write(alumno.getProfesion());
                bufferedWriter.write(",");
                bufferedWriter.write(alumno.getTelefono());
                bufferedWriter.write(",");
                bufferedWriter.write(alumno.getSobreMi());
                bufferedWriter.write(",");
                bufferedWriter.write(alumno.getHabilidades());
                bufferedWriter.write(",");
                bufferedWriter.write(alumno.getEducacion());
                bufferedWriter.write(",");
                bufferedWriter.write(alumno.getFechagrad());
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    return "Archivo guardado";
    }

 */
    @PostMapping("/saveToFile")
    public String save(){
        ObjectOutputStream dataAlumnos;
        try{
            dataAlumnos = new ObjectOutputStream(new FileOutputStream("data.txt"));
            for(Alumno a: alumnos){
                dataAlumnos.writeObject(alumnos);
            }
            dataAlumnos.close();
        }
        catch (IOException e){

        }
        return "Archivo guardado";
    }
    @PostMapping ("/recuperarData")
    public String retrieve(){
       try{
           ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("data.txt"));
           Object objeto = inputStream.readObject();
           inputStream.close();
           ArrayList<Alumno> alumnosRecuperado = (ArrayList<Alumno>) objeto;
           alumnos.addAll(alumnosRecuperado);
       } catch (EOFException ex) {
        // se llegó al final del archivo
    } catch (IOException ex) {
        ex.printStackTrace();
    } catch (ClassNotFoundException ex) {
        ex.printStackTrace();
    }
       return "Archivo cargado";
    }
/*
    @PostMapping("/saveToFile")
    public ResponseEntity<Void> saveAlumnos(@RequestBody Alumno al) throws IOException{
        String path = "data.json";
        ObjectMapper objectMapper = new ObjectMapper();
        File archivo = new File(path);
        objectMapper.writeValue(archivo,al);
        return ResponseEntity.ok().build();
    }
*/

    @GetMapping("/readFromFile")
    public void read() throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream("output.txt");
        ObjectInputStream ois = new ObjectInputStream(fin);
        alumnos = (ArrayList<Alumno>)ois.readObject();
        fin.close();
    }

    @PostMapping("/insertar")
    public void insertar(@RequestBody String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Alumno al = mapper.readValue(json, Alumno.class);
            for (Alumno a : alumnos) {
                if (a.getId() == al.getId()) {
                    break;
                }
            }
            alumnos.add(new Alumno(al.getId(), al.getNombre(), al.getDireccion(), al.getCorreo(), al.getProfesion(), al.getTelefono(),
                    al.getSobreMi(), al.getHabilidades(), al.getEducacion(), al.getFechagrad()));
        } catch(IOException e){
            e.printStackTrace();
        }

    }

   /* @GetMapping("/readFromFile2")
    public ArrayList<Alumno> read() throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream("temporal.txt");
        ObjectInputStream ois = new ObjectInputStream(fin);
        alumnos = (ArrayList<Alumno>)ois.readObject();
        fin.close();
        return alumnos;
    } */
}
