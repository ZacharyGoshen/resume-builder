import { Component, OnInit } from '@angular/core';
import {Resume} from "../../models/resume";
import {ResumeService} from "../../services/resume.service";

@Component({
  selector: 'app-resume',
  templateUrl: './resume.component.html',
  styleUrls: ['./resume.component.css']
})
export class ResumeComponent implements OnInit {

  resume: Resume;

  constructor(private resumeService: ResumeService) { }

  ngOnInit(): void {
    this.resumeService.getResumes().subscribe(resumes => this.resume = resumes[0]);
  }

}
