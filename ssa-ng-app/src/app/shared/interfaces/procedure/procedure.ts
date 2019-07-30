export interface Procedure {
    status: string;
    doctorId: number;
    patientId: number;
    description: string;
    plannedEndDate: string;
    plannedStartDate: string;
}

export interface ProcedureUpdate {
    id: number;
    status: string;
}
