/*
 *
 * Copyright 2018 EMBL - European Bioinformatics Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package uk.ac.ebi.ampt2d.commons.accession.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uk.ac.ebi.ampt2d.commons.accession.core.exceptions.AccessionCouldNotBeGeneratedException;
import uk.ac.ebi.ampt2d.commons.accession.core.exceptions.AccessionDoesNotExistException;
import uk.ac.ebi.ampt2d.commons.accession.core.exceptions.AccessionIsNotPendingException;
import uk.ac.ebi.ampt2d.commons.accession.core.exceptions.HashAlreadyExistsException;
import uk.ac.ebi.ampt2d.commons.accession.core.exceptions.MissingUnsavedAccessionsException;

/**
 * Spring {@link RestControllerAdvice} bean to handle exception from the application at rest level and return
 * a specific response with an error message.
 */
@RestControllerAdvice(assignableTypes = BasicRestController.class)
public class BasicRestControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(BasicRestControllerAdvice.class);

    @ExceptionHandler(value = {AccessionIsNotPendingException.class, AccessionCouldNotBeGeneratedException.class,
            MissingUnsavedAccessionsException.class})
    public ResponseEntity<ErrorMessage> handleInternalServerErrors(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {AccessionDoesNotExistException.class})
    public ResponseEntity<ErrorMessage> handleNotFoundErrors(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.NOT_FOUND, ex),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {HashAlreadyExistsException.class})
    public ResponseEntity<ErrorMessage> handleConflictErrors(Exception ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.CONFLICT, ex),
                HttpStatus.CONFLICT);
    }

}